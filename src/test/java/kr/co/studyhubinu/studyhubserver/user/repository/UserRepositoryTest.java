package kr.co.studyhubinu.studyhubserver.user.repository;

import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.support.fixture.UserEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.repository.RepositoryTest;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@RepositoryTest
@ActiveProfiles("dev")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 회원가입")
    void 유저가_회원가입한다() {
        // given
        UserEntity user = UserEntityFixture.YEONGJAE.UserEntity_생성(1L);

        // when
        UserEntity saveUser = userRepository.save(user);

        // then
        assertAll(
                () -> assertThat(saveUser.equals(user))
        );
    }

    @Test
    @DisplayName("유저 식별자 값으로 조회")
    void 식별자값으로_유저를_조회한다() {
        // given
        UserEntity user = UserEntityFixture.YEONGJAE.UserEntity_생성(1L);
        userRepository.save(user);

        // when
        UserEntity findUserById = userRepository.findById(1L).orElseThrow(UserNotFoundException::new);

        // then
        assertAll(
                () -> assertThat(findUserById.equals(user))
        );
    }

    @Test
    @DisplayName("유저 이메일로 조회")
    void 이메일로_유저를_조회한다() {
        // given
        UserEntity user = UserEntityFixture.YEONGJAE.UserEntity_생성(1L);
        userRepository.save(user);

        // when
        UserEntity findUserByEmail = userRepository.findByEmail("xxx@inu.ac.kr").orElseThrow(UserNotFoundException::new);

        // then
        assertAll(
                () -> assertThat(findUserByEmail.equals(user))
        );
    }

    @Test
    @DisplayName("유저 닉네임으로 조회")
    void 닉네임으로_유저를_조회한다() {
        // given
        UserEntity user = UserEntityFixture.YEONGJAE.UserEntity_생성(1L);
        userRepository.save(user);

        // when
        UserEntity findUserByNickname = userRepository.findByNickname("LilJay").orElseThrow(UserNotFoundException::new);

        // then
        assertAll(
                () -> assertThat(findUserByNickname.equals(user))
        );
    }

    @Test
    @DisplayName("유저 닉네임으로 조회")
    void 이메일로_유저_존재여부를_확인한다() {
        // given
        UserEntity user = UserEntityFixture.YEONGJAE.UserEntity_생성(1L);
        userRepository.save(user);

        // when
        boolean exist = userRepository.existsByEmail("xxx@inu.ac.kr");
        boolean nonExist = userRepository.existsByEmail("나는아니야@inu.ac.kr");

        // then
        assertAll(
                () -> assertThat(exist).isTrue(),
                () -> assertThat(nonExist).isFalse()
        );
    }
}