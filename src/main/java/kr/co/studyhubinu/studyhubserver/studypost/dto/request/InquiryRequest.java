package kr.co.studyhubinu.studyhubserver.studypost.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor
public class InquiryRequest {

    @Nullable
    private String inquiryText;
    private boolean titleAndMajor;
    private boolean hot;

    @Builder
    public InquiryRequest(@Nullable String inquiryText, boolean titleAndMajor, boolean hot) {
        this.inquiryText = inquiryText;
        this.titleAndMajor = titleAndMajor;
        this.hot = hot;
    }
}
