package kr.co.studyhubinu.studyhubserver.user.service;

import kr.co.studyhubinu.studyhubserver.exception.user.AlreadyExistUserException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.support.fixture.UserEntityFixture;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.*;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;


    @Test
    void 동일한_이메일로_회원가입한다() {
        // given
        UserEntity user1 = UserEntityFixture.DONGWOO.UserEntity_생성(1L);
        UserEntity user2 = UserEntityFixture.DONGWOO.UserEntity_생성(2L);
        SignUpInfo signUpInfo = new SignUpInfo(user2.getNickname(), user2.getEmail(), user2.getPassword(),
                user2.getMajor(), user2.getGender());

        // when
        BDDMockito.given(userRepository.existsByEmail(user1.getEmail())).willReturn(true);

        // then
        assertThatThrownBy(() -> userService.registerUser(signUpInfo))
                .isExactlyInstanceOf(AlreadyExistUserException.class);
    }

    @Test
    void 잘못된_식별자로_유저를_업데이트한다() {
        // given
        UserEntity user1 = UserEntityFixture.DONGWOO.UserEntity_생성(1L);
        UpdateUserInfo updateUserInfo = UpdateUserInfo.builder()
                .userId(2L)
                .major(user1.getMajor())
                .imageUrl("asdasd")
                .nickname("나는이주원이다")
                .build();

        // when
        BDDMockito.given(userRepository.findById(2L)).willReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> userService.updateUser(updateUserInfo))
                .isExactlyInstanceOf(UserNotFoundException.class);
    }

    @Test
    void 잘못된_식별자로_유저를_삭제_조회한다() {
        // given
        Long userId = 1L;

        // when
        BDDMockito.given(userRepository.findById(1L)).willReturn(Optional.empty());

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
        BDDMockito.given(userRepository.findById(1L)).willReturn(Optional.ofNullable(userEntity));

        // then
        assertThatThrownBy(() -> userService.updatePassword(updatePasswordInfo))
                .isExactlyInstanceOf(UserNotAccessRightException.class);
    }

    @Test
    void 유저가_닉네임을_변경한다() {
        // given
        UserEntity user = UserEntityFixture.YEONGJAE.UserEntity_생성(1L);
        UpdateNicknameInfo updateNicknameInfo = UpdateNicknameInfo.builder()
                .nickname("messi")
                .userId(user.getId())
                .build();
        BDDMockito.given(userRepository.findById(1L)).willReturn(Optional.ofNullable(user));

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
        BDDMockito.given(userRepository.findById(1L)).willReturn(Optional.ofNullable(user));

        // when
        userService.updateMajor(updateMajorInfo);

        // then
        Assertions.assertThat(user.getMajor()).isEqualTo(updateMajorInfo.getMajor());
    }

}
