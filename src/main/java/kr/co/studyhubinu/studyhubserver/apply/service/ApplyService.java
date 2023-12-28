package kr.co.studyhubinu.studyhubserver.apply.service;

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

//    public void approve(Long userId, Long studyId) {
//        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
//        StudyEntity study = studyRepository.findById(studyId).orElseThrow();
//        ApplyEntity applyEntity = new ApplyEntity(true, study, user);
//        applyRepository.save(applyEntity);
//    }
//
//    public void refuse(Long userId, Long studyId) {
//        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
//        StudyEntity study = studyRepository.findById(studyId).orElseThrow();
//        ApplyEntity applyEntity = new ApplyEntity(false, study, user);
//        applyRepository.save(applyEntity);
//    }
}
