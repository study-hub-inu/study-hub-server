package kr.co.studyhubinu.studyhubserver.user.service;

import kr.co.studyhubinu.studyhubserver.config.PasswordEncoder;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtProvider;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtResponseDto;
import kr.co.studyhubinu.studyhubserver.exception.common.CustomException;
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

    private static final String BASIC_PROFILE_IMAGE = "https://studyhub-s3.s3.ap-northeast-2.amazonaws.com/avatar_l%401x.png";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserActivityFinder userActivityFinder;
    private final UserDeleter userDeleter;
    private final JwtProvider jwtProvider;

    @Transactional
    public void registerUser(SignUpInfo signUpInfo) {
        validateExistUserEmail(signUpInfo);
        UserEntity userEntity = signUpInfo.toEntity(passwordEncoder, BASIC_PROFILE_IMAGE);
        userRepository.save(userEntity);
    }

    public void updateUser(UpdateUserInfo info) {
        UserEntity user = getUserById(info.getUserId(), new UserNotFoundException());
        user.update(info);
    }

    @Transactional
    public void deleteUser(Long userId) {
        UserEntity user = getUserById(userId, new UserNotFoundException());
        userDeleter.deleteUserRelatedData(user);
    }
  
    public GetUserResponse getUser(Long userId) {
        UserEntity user = getUserById(userId, new UserNotFoundException());
        UserActivityCountData data = userActivityFinder.countUserActivity(userId);
        return new GetUserResponse(user, data);
    }

    @Transactional
    public void updateNickname(UpdateNicknameInfo info) {
        UserEntity user = getUserById(info.getUserId(), new UserNotFoundException());
        user.updateNickname(info.getNickname());
    }

    public void nicknameDuplicationValid(String nickname) {
        userRepository.findByNickname(nickname).ifPresent(existingUser -> {
            throw new UserNicknameDuplicateException();
        });
    }

    @Transactional
    public void updateMajor(UpdateMajorInfo info) {
        UserEntity user = getUserById(info.getUserId(), new UserNotFoundException());
        user.updateMajor(info.getMajor());
    }

    @Transactional
    public void updatePassword(UpdatePasswordInfo info) {
        UserEntity user = getUserById(info.getUserId(), new UserNotFoundException());

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
        UserEntity userEntity = getUserByEmail(signInRequest, new UserNotFoundException());
        userVerify(userEntity, signInRequest);

        return jwtProvider.createJwtResponseDto(userEntity.getId());
    }

    private void userVerify(UserEntity userEntity, SignInRequest signInRequest) {
        String userPassword = passwordEncoder.encode(signInRequest.getEmail(), signInRequest.getPassword());

        if(!passwordEncoder.matches(userPassword, userEntity.getPassword())) {
            throw new PasswordNotFoundException();
        }
    }

    private UserEntity getUserByEmail(SignInRequest signInRequest, CustomException customException) {
        return userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> customException);
    }

    private UserEntity getUserById(Long userId, CustomException customException) {
        return userRepository.findById(userId).orElseThrow(() -> customException);
    }

    private void validateExistUserEmail(SignUpInfo signUpInfo) {
        if (userRepository.existsByEmail(signUpInfo.getEmail())) {
            throw new AlreadyExistUserException();
        }
    }
}
