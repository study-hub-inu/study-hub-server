package kr.co.studyhubinu.studyhubserver.user.service;

import kr.co.studyhubinu.studyhubserver.config.PasswordEncoder;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtProvider;
import kr.co.studyhubinu.studyhubserver.config.jwt.JwtResponseDto;
import kr.co.studyhubinu.studyhubserver.exception.user.AlreadyExistUserException;
import kr.co.studyhubinu.studyhubserver.exception.user.PasswordNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.support.fixture.UserEntityFixture;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.*;
import kr.co.studyhubinu.studyhubserver.user.dto.request.SignInRequest;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;


    @Test
    void 동일한_이메일로_회원가입한다() {
        // given
        UserEntity user = UserEntityFixture.DONGWOO.UserEntity_생성(2L);
        SignUpInfo signUpInfo = new SignUpInfo(user.getNickname(), user.getEmail(), user.getPassword(),
                user.getMajor(), user.getGender());

        // when
        given(userRepository.existsByEmail(user.getEmail())).willReturn(true);

        // then
        assertThatThrownBy(() -> userService.registerUser(signUpInfo))
                .isExactlyInstanceOf(AlreadyExistUserException.class);
    }

    @Test
    void 잘못된_식별자로_유저를_업데이트한다() {
        // given
        UpdateUserInfo updateUserInfo = UpdateUserInfo.builder()
                .userId(2L)
                .build();

        // when
        given(userRepository.findById(2L)).willReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> userService.updateUser(updateUserInfo))
                .isExactlyInstanceOf(UserNotFoundException.class);
    }

    @Test
    void 잘못된_식별자로_유저를_삭제_조회한다() {
        // given
        Long userId = 1L;

        // when
        given(userRepository.findById(1L)).willReturn(Optional.empty());

        // then
        assertAll(
                () -> assertThatThrownBy(() -> userService.deleteUser(userId))
                        .isExactlyInstanceOf(UserNotFoundException.class),
                () -> assertThatThrownBy(() -> userService.getUser(userId))
                        .isExactlyInstanceOf(UserNotFoundException.class)
        );
    }

    @Test
    void 현재_비밀번호를_인증하지않고_비밀번호_변경을_시도한다() {
        // given
        UserEntity userEntity = UserEntityFixture.JOOWON.UserEntity_생성(1L);
        UpdatePasswordInfo updatePasswordInfo = new UpdatePasswordInfo(1L, "liljay", false);

        // when
        given(userRepository.findById(1L)).willReturn(Optional.ofNullable(userEntity));

        // then
        assertThatThrownBy(() -> userService.updatePassword(updatePasswordInfo))
                .isExactlyInstanceOf(UserNotAccessRightException.class);
    }

    @Test
    void 유저가_닉네임을_변경한다() {
        // given
        UserEntity user = UserEntityFixture.YEONGJAE.UserEntity_생성(1L);
        UpdateNicknameInfo updateNicknameInfo = UpdateNicknameInfo.builder()
                .nickname("나사나이이영재")
                .userId(user.getId())
                .build();
        given(userRepository.findById(1L)).willReturn(Optional.ofNullable(user));

        // when
        userService.updateNickname(updateNicknameInfo);

        // then
        Assertions.assertThat(user.getNickname()).isEqualTo(updateNicknameInfo.getNickname());
    }

    @Test
    void 유저_전공을_변경한다() {
        // given
        UserEntity user = UserEntityFixture.YEONGJAE.UserEntity_생성(1L);
        UpdateMajorInfo updateMajorInfo = UpdateMajorInfo.builder()
                .major(MajorType.ARCHITECTURAL_ENGINEERING)
                .userId(user.getId())
                .build();
        given(userRepository.findById(1L)).willReturn(Optional.ofNullable(user));

        // when
        userService.updateMajor(updateMajorInfo);

        // then
        Assertions.assertThat(user.getMajor()).isEqualTo(updateMajorInfo.getMajor());
    }

    @Test
    void 유저가_로그인한다() {
        // given
        given(jwtProvider.createJwtResponseDto(any())).willReturn(JwtResponseDto.builder()
                        .accessToken("Bearer access")
                        .refreshToken("Bearer refresh")
                        .build());
        given(userRepository.findByEmail(any())).willReturn(Optional.ofNullable(UserEntity.builder()
                .build()));
        given(passwordEncoder.encode(any(), any())).willReturn("yj");
        given(passwordEncoder.matches(any(), any())).willReturn(true);

        // when
        JwtResponseDto jwtResponseDto = userService.loginUser(SignInRequest.builder().build());

        // then
        Assertions.assertThat(jwtResponseDto.getAccessToken()).isEqualTo("Bearer access");
        Assertions.assertThat(jwtResponseDto.getRefreshToken()).isEqualTo("Bearer refresh");
    }

    @Test
    void 비밀번호_검증_실패() {
        // given
        given(passwordEncoder.matches(any(), any())).willReturn(false);
        given(userRepository.findById(any())).willReturn(Optional.ofNullable(UserEntity.builder().build()));

        // when, then
        assertThatThrownBy(() -> userService.verifyPassword(1L, "liljay")).isInstanceOf(PasswordNotFoundException.class);
    }

}
