package kr.co.studyhubinu.studyhubserver.studypost.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@Builder
public class InquiryRequest {

    @Nullable
    private final String inquiryText;
    private final boolean titleAndMajor;
    private final boolean hot;
}
