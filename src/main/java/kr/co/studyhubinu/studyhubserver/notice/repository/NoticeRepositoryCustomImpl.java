package kr.co.studyhubinu.studyhubserver.notice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.notice.domain.QNoticeEntity;
import kr.co.studyhubinu.studyhubserver.notice.dto.response.FindNoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.studyhubinu.studyhubserver.notice.domain.QNoticeEntity.noticeEntity;

@Repository
@RequiredArgsConstructor
public class NoticeRepositoryCustomImpl implements NoticeRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<FindNoticeResponse> findNoticeAll(Pageable pageable) {
        QNoticeEntity notice = noticeEntity;

        JPAQuery<FindNoticeResponse> data = jpaQueryFactory
                .select(Projections.constructor(
                        FindNoticeResponse.class,
                        notice.id.as("noticeId"), notice.title, notice.content, notice.createdDate
                        )
                )
                .from(notice)
                .orderBy(notice.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);
        return data.fetch();
    }
}
