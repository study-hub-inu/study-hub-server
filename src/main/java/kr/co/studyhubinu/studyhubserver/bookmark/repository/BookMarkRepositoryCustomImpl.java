package kr.co.studyhubinu.studyhubserver.bookmark.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.bookmark.domain.QBookMarkEntity;
import kr.co.studyhubinu.studyhubserver.study.domain.QStudyPostEntity;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.studyhubinu.studyhubserver.bookmark.domain.QBookMarkEntity.*;
import static kr.co.studyhubinu.studyhubserver.study.domain.QStudyPostEntity.*;

@RequiredArgsConstructor
@Repository
public class BookMarkRepositoryCustomImpl implements BookMarkRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<StudyPostEntity> findPostByBookMark(Long userId) {
        QBookMarkEntity bookMark = bookMarkEntity;
        QStudyPostEntity post = studyPostEntity;

        return (Slice<StudyPostEntity>) jpaQueryFactory
                .select(post)
                .from(post)
                .join(bookMark)
                .where(bookMark.userId.eq(userId), bookMark.postId.eq(post.id))
                .limit(100);
    }

}
