package kr.co.studyhubinu.studyhubserver.notice.repository;

import kr.co.studyhubinu.studyhubserver.notice.dto.response.FindTermsOfUsesResponse;

import java.util.List;

public interface TermsOfUseRepositoryCustom {
    List<FindTermsOfUsesResponse> findAllTermsOfUse();
}
