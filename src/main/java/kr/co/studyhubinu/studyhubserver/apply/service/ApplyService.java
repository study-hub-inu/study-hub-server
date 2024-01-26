package kr.co.studyhubinu.studyhubserver.apply.service;

import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.ApplyUserData;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.ParticipateApplyData;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.EnrollApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.FindApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.UpdateApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.response.FindApplyResponse;
import kr.co.studyhubinu.studyhubserver.apply.dto.response.FindParticipateApplyResponse;
import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import kr.co.studyhubinu.studyhubserver.apply.repository.ApplyRepository;
import kr.co.studyhubinu.studyhubserver.common.dto.Converter;
import kr.co.studyhubinu.studyhubserver.exception.apply.ApplyNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.apply.SameUserRequestException;
import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundExceptionByStudyId;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.study.repository.StudyRepository;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kr.co.studyhubinu.studyhubserver.apply.enums.Inspection.ACCEPT;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ApplyService {

    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final ApplyRepository applyRepository;
    private final StudyPostRepository studyPostRepository;

    @Transactional
    public void enroll(Long userId, EnrollApplyRequest request) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        StudyEntity study = studyRepository.findById(request.getStudyId()).orElseThrow();
        validateSameRequest(user, study);

        applyRepository.save(ApplyEntity.of(user.getId(), study.getId(), request.getIntroduce()));
    }

    @Transactional
    public void update(UpdateApplyRequest request) {
        UserEntity user = userRepository.findById(request.getUserId()).orElseThrow(UserNotFoundException::new);
        StudyEntity study = studyRepository.findById(request.getStudyId()).orElseThrow();
        ApplyEntity applyEntity = applyRepository.findByUserIdAndStudyId(user.getId(), study.getId()).orElseThrow(ApplyNotFoundException::new);

        isAccept(request);
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

    public FindParticipateApplyResponse getParticipateApply(final Long userId, final int page, final int size) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        final Pageable pageable = PageRequest.of(page, size);
        Long totalCount = applyRepository.countByUserIdAndInspection(user.getId(), ACCEPT);
        Slice<ParticipateApplyData> participateApplyData = Converter.toSlice
                (pageable, applyRepository.findByUserIdAndInspection(user.getId(), pageable));
        return new FindParticipateApplyResponse(totalCount, participateApplyData);
    }

    private void isAccept(UpdateApplyRequest request) {
        if(request.getInspection().equals(ACCEPT)) {
            StudyPostEntity post = studyPostRepository.findByStudyId(request.getStudyId()).orElseThrow(PostNotFoundExceptionByStudyId::new);
            post.minusRemainingSeat();
        }
    }
}
