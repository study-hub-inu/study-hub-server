package kr.co.studyhubinu.studyhubserver.apply.service;

import kr.co.studyhubinu.studyhubserver.apply.dto.request.ChangeApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.EnrollApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import kr.co.studyhubinu.studyhubserver.apply.repository.ApplyRepository;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.study.StudyRepository;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import lombok.RequiredArgsConstructor;
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
        ApplyEntity apply = toApplyEntity(user, study);

        applyRepository.save(apply);
    }

    private ApplyEntity toApplyEntity(UserEntity user, StudyEntity study) {
        return ApplyEntity.builder()
                .user(user)
                .study(study)
                .inspection(Inspection.STANDBY)
                .build();
    }

    public void update(ChangeApplyRequest request) {
        UserEntity user = userRepository.findById(request.getUserId()).orElseThrow(UserNotFoundException::new);
        StudyEntity study = studyRepository.findById(request.getStudyId()).orElseThrow();
        ApplyEntity applyEntity = applyRepository.findByUserAndStudy(user, study);
        applyEntity.update(request.getInspection());
    }
}
