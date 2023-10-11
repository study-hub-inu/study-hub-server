package kr.co.studyhubinu.studyhubserver.studypost.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.bookmark.domain.QBookMarkEntity;
import kr.co.studyhubinu.studyhubserver.study.domain.QStudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetBookmarkedPostsData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByAll;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByRemainingSeat;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByString;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.studyhubinu.studyhubserver.bookmark.domain.QBookMarkEntity.bookMarkEntity;
import static kr.co.studyhubinu.studyhubserver.study.domain.QStudyPostEntity.*;

@Repository
@RequiredArgsConstructor
public class StudyPostRepositoryImpl implements StudyPostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<FindPostResponseByString> findByString(String title, MajorType major, String content, Pageable pageable) {
        QStudyPostEntity post = studyPostEntity;

        JPAQuery<FindPostResponseByString> studyPostDto = jpaQueryFactory
                .select(Projections.constructor(FindPostResponseByString.class,
                        post.id, post.major, post.title, post.content, post.studyPerson, post.studyPerson, post.close))
                .from(post)
                .orderBy(post.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);

        insertQuery(studyPostDto, title, major, content);

        return toSlice(pageable, studyPostDto.fetch());
    }

    @Override
    public Slice<FindPostResponseByAll> findByAll(Pageable pageable) {
        QStudyPostEntity post = studyPostEntity;

        JPAQuery<FindPostResponseByAll> studyPostDto = jpaQueryFactory
                .select(Projections.constructor(FindPostResponseByAll.class,
                        post.id, post.major, post.title, post.content, post.studyPerson, post.studyPerson, post.close))
                .from(post)
                .orderBy(post.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);

        return toSlice(pageable, studyPostDto.fetch());
    }

    @Override
    public Slice<GetBookmarkedPostsData> findPostsByBookmarked(Long userId, Pageable pageable) {
        QStudyPostEntity post = studyPostEntity;
        QBookMarkEntity bookMark = bookMarkEntity;

        JPAQuery<GetBookmarkedPostsData> studyPostDto = jpaQueryFactory.select(
                        Projections.constructor(GetBookmarkedPostsData.class,
                                post.id.as("postId"), post.major, post.title, post.content, post.remainingSeat, post.close))
                .from(post)
                .innerJoin(bookMark)
                .on(bookMark.postId.eq(post.id))
                .where(bookMark.userId.eq(userId))
                .orderBy(post.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);

        return toSlice(pageable, studyPostDto.fetch());
    }

    @Override
    public Slice<FindPostResponseByRemainingSeat> findPostsByRemainingSeat(Pageable pageable) {
        QStudyPostEntity post = studyPostEntity;

        JPAQuery<FindPostResponseByRemainingSeat> studyPostDto = jpaQueryFactory.select(
                Projections.constructor(FindPostResponseByRemainingSeat.class,
                        post.id.as("postId"), post.title, post.studyPerson, post.remainingSeat))
                .from(post)
                .orderBy(post.remainingSeat.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return toSlice(pageable, studyPostDto.fetch());
    }

    public void insertQuery(JPAQuery<FindPostResponseByString> studyPostDto, String title, MajorType major, String content) {
        QStudyPostEntity post = studyPostEntity;

        if(title != null) {
            studyPostDto.where(post.title.like(title + "%"));
        }
        if(major != null) {
            studyPostDto.where(post.major.eq(major));
        }
        if(content != null) {
            studyPostDto.where(post.content.like("%" + content + "%"));
        }
    }

    public static <T> Slice<T> toSlice(final Pageable pageable, final List<T> items) {
        if (items.size() > pageable.getPageSize()) {
            items.remove(items.size()-1);
            return new SliceImpl<>(items, pageable, true);
        }
        return new SliceImpl<>(items, pageable, false);
    }
}
