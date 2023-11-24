package kr.co.studyhubinu.studyhubserver.studypost.dto.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InquiryRequestTest {

    @Test
    void builder() {
        // given
        InquiryRequest inquiryRequest = InquiryRequest.builder()
                .inquiryText("안녕")
                .build();

        // when, then
        assertThat(inquiryRequest.getInquiryText()).isEqualTo("안녕");
        Assertions.assertThat(inquiryRequest.isTitleAndMajor()).isFalse();
    }
}