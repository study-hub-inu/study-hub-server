package kr.co.studyhubinu.studyhubserver.studypost.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class FindRecommendPostsResponse {

    private List<String> recommendList;

    public FindRecommendPostsResponse(List<String> recommendList) {
        this.recommendList = recommendList;
    }
}
