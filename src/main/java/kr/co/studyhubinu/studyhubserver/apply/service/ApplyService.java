package kr.co.studyhubinu.studyhubserver.apply.service;

import kr.co.studyhubinu.studyhubserver.apply.dto.data.ApplyUserData;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.EnrollApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.FindApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.UpdateApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.response.FindApplyResponse;
import kr.co.studyhubinu.studyhubserver.apply.repository.ApplyRepository;
import kr.co.studyhubinu.studyhubserver.common.dto.Converter;
import kr.co.studyhubinu.studyhubserver.exception.apply.ApplyNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.apply.SameUserRequestException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.study.StudyRepository;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplyService {

    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final ApplyRepository applyRepository;

    public void enroll(Long userId, EnrollApplyRequest request) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        StudyEntity study = studyRepository.findById(request.getStudyId()).orElseThrow();
        validateSameRequest(user, study);

        applyRepository.save(ApplyEntity.of(user.getId(), study.getId(), request.getIntroduce()));
    }

    public void update(UpdateApplyRequest request) {
        UserEntity user = userRepository.findById(request.getUserId()).orElseThrow(UserNotFoundException::new);
        StudyEntity study = studyRepository.findById(request.getStudyId()).orElseThrow();
        ApplyEntity applyEntity = applyRepository.findByUserIdAndStudyId(user.getId(), study.getId()).orElseThrow(ApplyNotFoundException::new);
        applyEntity.update(request.getInspection());
    }

    public FindApplyResponse findApply(FindApplyRequest request, final int page, final int size) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<ApplyUserData> userData = Converter.toSlice(pageable, applyRepository.findByStudy(request.getStudyId(), pageable));

        return new FindApplyResponse((long) size, userData);
    }

    private void validateSameRequest(UserEntity user, StudyEntity study) {
        if(applyRepository.findByUserIdAndStudyId(user.getId(), study.getId()).isPresent()) {
            throw new SameUserRequestException();
        }
    }
}
