package kr.co.studyhubinu.studyhubserver.studypost.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class InquiryRequest {

    @Nullable
    private final String inquiryText;
    private final boolean titleAndMajor;
    private final boolean hot;

    @Builder
    public InquiryRequest(@Nullable String inquiryText, boolean titleAndMajor, boolean hot) {
        this.inquiryText = inquiryText;
        this.titleAndMajor = titleAndMajor;
        this.hot = hot;
    }
}
