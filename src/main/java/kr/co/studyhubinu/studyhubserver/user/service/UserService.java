package kr.co.studyhubinu.studyhubserver.user.service;

import kr.co.studyhubinu.studyhubserver.exception.user.AlreadyExistUserException;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.SignUpInfo;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UpdateUserInfo;
import kr.co.studyhubinu.studyhubserver.user.dto.response.GetUserResponse;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registerUser(SignUpInfo signUpInfo) {
        log.info(signUpInfo.getEmail());

        if (userRepository.existsByEmail(signUpInfo.getEmail())) {
            throw new AlreadyExistUserException();
        }

        UserEntity userEntity = signUpInfo.toEntity(bCryptPasswordEncoder);

        userRepository.save(userEntity);
    }

    public void updateUser(UpdateUserInfo info) {

        UserEntity user = userRepository.findById(info.getUserId()).orElseThrow(RuntimeException::new);

        user.update(info);
    }


    public GetUserResponse getUser(Long userId) {

        UserEntity user = userRepository.findById(userId).orElseThrow(RuntimeException::new);

        return new GetUserResponse(user);

    }
}
