package kr.co.studyhubinu.studyhubserver.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PasswordEncoderTest {

    PasswordEncoder passwordEncoder = new PasswordEncoder();

    @Test
    @DisplayName("같은 비밀번호에 대해 다른 해시값을 반환한다.")
    void encrypt() {

        // given
        String dongWooEmail = "kdw@inu.ac.kr";
        String juWonEmail = "lyj@inu.ac.kr";

        String dongWooPassWord = "helloStudy@@";
        String juWonPassWord = "helloStudy@@";

        // when
        String dongWooEncode = passwordEncoder.encode(dongWooEmail, dongWooPassWord);
        String juWonEncode = passwordEncoder.encode(juWonEmail, juWonPassWord);

        assertThat(dongWooEncode).isNotEqualTo(juWonEncode);
    }

}