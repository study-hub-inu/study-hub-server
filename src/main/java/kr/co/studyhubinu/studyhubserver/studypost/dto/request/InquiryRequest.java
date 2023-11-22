package kr.co.studyhubinu.studyhubserver.studypost.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InquiryRequest {

    private final String inquiryText;
    private final boolean titleAndMajor;
    private final boolean hot;
}
