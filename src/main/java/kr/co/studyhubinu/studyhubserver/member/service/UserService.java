package kr.co.studyhubinu.studyhubserver.member.service;

import kr.co.studyhubinu.studyhubserver.member.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.member.dto.data.GeneralSignUpInfo;
import kr.co.studyhubinu.studyhubserver.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void registerUser(GeneralSignUpInfo signUpInfo) {
        log.info(signUpInfo.getEmail());
        if (userRepository.existsByEmail(signUpInfo.getEmail())) {
            throw new RuntimeException();
        }

        UserEntity user = new UserEntity(signUpInfo);

        userRepository.save(user);
    }

}
