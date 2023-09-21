package kr.co.studyhubinu.studyhubserver.study.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.study.domain.QStudyPostEntity;
import kr.co.studyhubinu.studyhubserver.study.dto.response.*;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;


import java.util.Collections;
import java.util.List;

import static kr.co.studyhubinu.studyhubserver.bookmark.domain.QBookMarkEntity.bookMarkEntity;
import static kr.co.studyhubinu.studyhubserver.study.domain.QStudyPostEntity.*;

@Repository
@RequiredArgsConstructor
public class StudyPostRepositoryImpl implements StudyPostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<FindPostResponseByString> findByTitle(String title, Pageable pageable) {
        QStudyPostEntity post = studyPostEntity;

        JPAQuery<FindPostResponseByString> studyPostDto = jpaQueryFactory
                .select(Projections.constructor(FindPostResponseByString.class,
                        post.id, post.major, post.title, post.content, post.studyPerson, post.studyPerson, post.close))
                .from(post)
                .where(post.title.like(title + "%"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return findByQuery(studyPostDto, pageable);
    }

    @Override
    public Slice<FindPostResponseByMajor> findByMajor(MajorType major, Pageable pageable) {
        QStudyPostEntity post = studyPostEntity;

        JPAQuery<FindPostResponseByMajor> studyPostDto = jpaQueryFactory
                .select(Projections.constructor(FindPostResponseByMajor.class,
                        post.id, post.major, post.title, post.content, post.studyPerson, post.studyPerson, post.close))
                .from(post)
                .where(post.major.eq(major))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return findByQuery(studyPostDto, pageable);
    }

    @Override
    public Slice<FindPostResponseByAll> findByAll(Pageable pageable) {
        QStudyPostEntity post = studyPostEntity;

        JPAQuery<FindPostResponseByAll> studyPostDto = jpaQueryFactory
                .select(Projections.constructor(FindPostResponseByAll.class,
                        post.id, post.major, post.title, post.content, post.studyPerson, post.studyPerson, post.close))
                .from(post)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return findByQuery(studyPostDto, pageable);
    }

    @Override
    public Slice<FindPostResponseByContent> findByContent(String content, Pageable pageable) {
        QStudyPostEntity post = studyPostEntity;

        JPAQuery<FindPostResponseByContent> studyPostDto = jpaQueryFactory
                .select(Projections.constructor(FindPostResponseByContent.class,
                        post.id, post.major, post.title, post.content, post.studyPerson, post.studyPerson, post.close))
                .from(post)
                .where(post.content.like("%" + content + "%"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return findByQuery(studyPostDto, pageable);
    }

    @Override
    public Slice<GetBookmarkedPostsResponse> findPostsByBookmarked(Long userId, Pageable pageable) {
        List<GetBookmarkedPostsResponse> lists = jpaQueryFactory.select(
                        Projections.bean(GetBookmarkedPostsResponse.class,
                                studyPostEntity.id.as("postId"),
                                studyPostEntity.major,
                                studyPostEntity.title,
                                studyPostEntity.content,
                                studyPostEntity.remainingSeat,
                                studyPostEntity.close
                        )
                )
                .from(studyPostEntity)
                .innerJoin(bookMarkEntity)
                .on(bookMarkEntity.postId.eq(studyPostEntity.id))
                .where(bookMarkEntity.userId.eq(userId))
                .orderBy(studyPostEntity.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return toSlice(pageable, lists);
    }

    public <T> Slice<T> findByQuery(JPAQuery<T> query, Pageable pageable) {
        Slice<T> sliceDto = toSlice(pageable, query.fetch());

        if(sliceDto.isEmpty()) {
            return new SliceImpl<>(Collections.emptyList(), pageable, false);
        }

        return new SliceImpl<>(query.fetch(), pageable, sliceDto.hasNext());
    }

    public static <T> Slice<T> toSlice(final Pageable pageable, final List<T> items) {
        if (items.size() > pageable.getPageSize()) {
            items.remove(items.size() - 1);
            return new SliceImpl<>(items, pageable, true);
        }
        return new SliceImpl<>(items, pageable, false);
    }


    //    @Override
//    public Slice<StudyPostEntity> findByBookMark(Pageable pageable) {
//        QStudyPostEntity post = studyPostEntity;
//        QBookMarkEntity bookMark = bookMarkEntity;
//
//        JPAQuery<StudyPostEntity> studyPostDto = jpaQueryFactory
//                .select(post).distinct()
//                .from(post)
//                .leftJoin(bookMark)
//                .on(bookMark.postId.eq(post.id))
//                .groupBy(post)
//                .orderBy(bookMark.id.count().desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize());
//
//        return findByQuery(studyPostDto, pageable);
//    }
}
