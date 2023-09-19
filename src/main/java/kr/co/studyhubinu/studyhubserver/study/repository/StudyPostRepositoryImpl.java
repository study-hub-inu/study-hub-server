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

        Slice<FindPostResponseByString> sliceDto = toSlice(pageable, studyPostDto.fetch());

        if(sliceDto.isEmpty()) {
            return new SliceImpl<>(Collections.emptyList(), pageable, false);
        }

        return new SliceImpl<>(studyPostDto.fetch(), pageable, sliceDto.hasNext());
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

        Slice<FindPostResponseByMajor> sliceDto = toSlice(pageable, studyPostDto.fetch());

        if(sliceDto.isEmpty()) {
            return new SliceImpl<>(Collections.emptyList(), pageable, false);
        }

        return new SliceImpl<>(studyPostDto.fetch(), pageable, sliceDto.hasNext());
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

        Slice<FindPostResponseByAll> sliceDto = toSlice(pageable, studyPostDto.fetch());

        if(sliceDto.isEmpty()) {
            return new SliceImpl<>(Collections.emptyList(), pageable, false);
        }

        return new SliceImpl<>(studyPostDto.fetch(), pageable, sliceDto.hasNext());
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

        Slice<FindPostResponseByContent> sliceDto = toSlice(pageable, studyPostDto.fetch());

        if(sliceDto.isEmpty()) {
            return new SliceImpl<>(Collections.emptyList(), pageable, false);
        }

        return new SliceImpl<>(studyPostDto.fetch(), pageable, sliceDto.hasNext());
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
//        Slice<StudyPostEntity> sliceDto = toSlice(pageable, studyPostDto.fetch());
//
//        if(sliceDto.isEmpty()) {
//            return new SliceImpl<>(Collections.emptyList(), pageable, false);
//        }
//
//        return new SliceImpl<>(studyPostDto.fetch(), pageable, sliceDto.hasNext());
//    }

    public static <T> Slice<T> toSlice(final Pageable pageable, final List<T> items) {
        if (items.size() > pageable.getPageSize()) {
            items.remove(items.size() - 1);
            return new SliceImpl<>(items, pageable, true);
        }
        return new SliceImpl<>(items, pageable, false);
    }
}
