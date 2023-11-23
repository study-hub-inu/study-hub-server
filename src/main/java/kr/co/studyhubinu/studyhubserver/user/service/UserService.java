package kr.co.studyhubinu.studyhubserver.user.service;

import kr.co.studyhubinu.studyhubserver.config.PasswordEncoder;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtProvider;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtResponseDto;
import kr.co.studyhubinu.studyhubserver.exception.user.AlreadyExistUserException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNicknameDuplicateException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.*;
import kr.co.studyhubinu.studyhubserver.user.domain.UserActivityFinder;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.domain.UserDeleter;
import kr.co.studyhubinu.studyhubserver.user.dto.data.*;
import kr.co.studyhubinu.studyhubserver.user.dto.request.SignInRequest;
import kr.co.studyhubinu.studyhubserver.user.dto.response.GetUserResponse;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserActivityFinder userActivityFinder;
    private final UserDeleter userDeleter;
    private final JwtProvider jwtProvider;

    @Transactional
    public void registerUser(SignUpInfo signUpInfo) {
        if (userRepository.existsByEmail(signUpInfo.getEmail())) {
            throw new AlreadyExistUserException();
        }
        UserEntity userEntity = signUpInfo.toEntity(passwordEncoder);
        userRepository.save(userEntity);
    }

    public void updateUser(UpdateUserInfo info) {
        UserEntity user = userRepository.findById(info.getUserId()).orElseThrow(UserNotFoundException::new);
        user.update(info);
    }

    @Transactional
    public void deleteUser(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userDeleter.deleteUserRelatedData(user);
    }
  
    public GetUserResponse getUser(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        UserActivityCountData data = userActivityFinder.countUserActivity(userId);
        return new GetUserResponse(user, data);
    }

    @Transactional
    public void updateNickname(UpdateNicknameInfo info) {
        UserEntity user = userRepository.findById(info.getUserId()).orElseThrow(UserNotFoundException::new);
        user.updateNickname(info.getNickname());
    }

    public void nicknameDuplicationValid(String nickname) {
        userRepository.findByNickname(nickname).ifPresent(existingUser -> {
            throw new UserNicknameDuplicateException();
        });
    }

    @Transactional
    public void updateMajor(UpdateMajorInfo info) {
        UserEntity user = userRepository.findById(info.getUserId()).orElseThrow(UserNotFoundException::new);
        user.updateMajor(info.getMajor());
    }

    @Transactional
    public void updatePassword(UpdatePasswordInfo info) {
        UserEntity user = userRepository.findById(info.getUserId()).orElseThrow(UserNotFoundException::new);

        if(!info.isAuth()) {
            throw new UserNotAccessRightException();
        }
        user.updatePassword(passwordEncoder.encode(user.getEmail(), info.getPassword()));
    }

    public void verifyPassword(Long userId, String password) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotAccessRightException::new);
        if(!passwordEncoder.matches(passwordEncoder.encode(user.getEmail(), password), user.getPassword())) {
            throw new PasswordNotFoundException();
        }
    }

    public JwtResponseDto loginUser(SignInRequest signInRequest) {
        UserEntity userEntity = findUser(signInRequest);
        userVerify(userEntity, signInRequest);

        return jwtProvider.createJwtResponseDto(userEntity.getId());
    }

    private void userVerify(UserEntity userEntity, SignInRequest signInRequest) {
        String userPassword = passwordEncoder.encode(signInRequest.getEmail(), signInRequest.getPassword());

        if(!passwordEncoder.matches(userPassword, userEntity.getPassword())) {
            throw new PasswordNotFoundException();
        }
    }

    private UserEntity findUser(SignInRequest signInRequest) {
        return userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(UserNotFoundException::new);
    }
}
