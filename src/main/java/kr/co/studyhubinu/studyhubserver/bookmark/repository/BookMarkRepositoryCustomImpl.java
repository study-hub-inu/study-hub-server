package kr.co.studyhubinu.studyhubserver.bookmark.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.bookmark.domain.QBookMarkEntity;
import kr.co.studyhubinu.studyhubserver.study.domain.QStudyPostEntity;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.studyhubinu.studyhubserver.bookmark.domain.QBookMarkEntity.*;
import static kr.co.studyhubinu.studyhubserver.study.domain.QStudyPostEntity.*;

@RequiredArgsConstructor
@Repository
public class BookMarkRepositoryCustomImpl implements BookMarkRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

}
