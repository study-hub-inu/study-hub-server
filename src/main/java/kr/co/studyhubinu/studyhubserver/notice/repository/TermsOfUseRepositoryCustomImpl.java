package kr.co.studyhubinu.studyhubserver.notice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.notice.domain.QTermsOfUseEntity;
import kr.co.studyhubinu.studyhubserver.notice.dto.response.FindTermsOfUsesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.studyhubinu.studyhubserver.notice.domain.QTermsOfUseEntity.termsOfUseEntity;


@Repository
@RequiredArgsConstructor
public class TermsOfUseRepositoryCustomImpl implements TermsOfUseRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<FindTermsOfUsesResponse> findAllTermsOfUse() {
        QTermsOfUseEntity termsOfUse = termsOfUseEntity;

        JPAQuery<FindTermsOfUsesResponse> data = jpaQueryFactory
                .select(Projections.constructor(
                        FindTermsOfUsesResponse.class,
                        termsOfUse.id.as("terms_of_use_id"), termsOfUse.title, termsOfUse.article, termsOfUse.content
                        )
                )
                .from(termsOfUse);

        return data.fetch();
    }
}
