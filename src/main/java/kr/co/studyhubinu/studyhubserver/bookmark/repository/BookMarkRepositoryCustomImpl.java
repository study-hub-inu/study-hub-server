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

    @Override
    public List<StudyPostEntity> findPostByBookMark(Long userId) {
        QBookMarkEntity bookMark = bookMarkEntity;
        QStudyPostEntity post = studyPostEntity;

        List<StudyPostEntity> studyPostEntityList = jpaQueryFactory
                .select(post)
                .from(post)
                .innerJoin(bookMark)
                .on(bookMark.postId.eq(post.id)) // on 절을 사용하여 조인 조건을 정의
                .where(bookMark.userId.eq(userId))
                .limit(100)
                .fetch();

        return studyPostEntityList;
    }

}
