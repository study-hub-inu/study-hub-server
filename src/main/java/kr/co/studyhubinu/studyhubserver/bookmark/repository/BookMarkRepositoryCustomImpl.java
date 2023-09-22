package kr.co.studyhubinu.studyhubserver.bookmark.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookMarkRepositoryCustomImpl implements BookMarkRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

}
