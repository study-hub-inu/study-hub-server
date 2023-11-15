package kr.co.studyhubinu.studyhubserver.userstudy.service;

import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.study.StudyRepository;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import kr.co.studyhubinu.studyhubserver.userstudy.domain.UserStudyEntity;
import kr.co.studyhubinu.studyhubserver.userstudy.repository.UserStudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserStudyService {

    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final UserStudyRepository userStudyRepository;

    public void approve(Long userId, Long studyId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        StudyEntity study = studyRepository.findById(studyId).orElseThrow();
        UserStudyEntity userStudyEntity = new UserStudyEntity(true, study, user);
        userStudyRepository.save(userStudyEntity);
    }

    public void refuse(Long userId, Long studyId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        StudyEntity study = studyRepository.findById(studyId).orElseThrow();
        UserStudyEntity userStudyEntity = new UserStudyEntity(false, study, user);
        userStudyRepository.save(userStudyEntity);
    }
}
