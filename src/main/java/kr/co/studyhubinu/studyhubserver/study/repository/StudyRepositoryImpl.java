package kr.co.studyhubinu.studyhubserver.study.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class StudyRepositoryImpl implements StudyRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

}
