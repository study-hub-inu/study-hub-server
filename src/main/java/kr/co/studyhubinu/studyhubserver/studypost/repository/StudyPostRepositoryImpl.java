package kr.co.studyhubinu.studyhubserver.studypost.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.bookmark.domain.QBookMarkEntity;
import kr.co.studyhubinu.studyhubserver.studypost.domain.QStudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetBookmarkedPostsData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.RelatedPostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByAll;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByRemainingSeat;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByString;
import kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserData;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static kr.co.studyhubinu.studyhubserver.bookmark.domain.QBookMarkEntity.bookMarkEntity;
import static kr.co.studyhubinu.studyhubserver.studypost.domain.QStudyPostEntity.*;
import static kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity.userEntity;

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

    @Override
    public Optional<PostData> findPostByIdAndUserId(Long postId, Long userId) {
        QStudyPostEntity post = studyPostEntity;
        QUserEntity user = userEntity;
        QBookMarkEntity bookmark = bookMarkEntity;

        JPAQuery<PostData> data = jpaQueryFactory
                .select(Projections.constructor(
                        PostData.class,
                        post.id.as("postId"),
                        post.title,
                        post.createdDate,
                        post.content,
                        post.major,
                        post.studyPerson,
                        post.filteredGender,
                        post.studyWay,
                        post.penalty,
                        post.penaltyWay,
                        post.studyStartDate,
                        post.studyEndDate,
                        post.remainingSeat,
                        Expressions.booleanTemplate("{0} = {1}", post.postedUserId, userId),
                        Expressions.booleanTemplate("{0} = {1}", bookmark.userId, userId),
                        Projections.constructor(
                                UserData.class,
                                user.id,
                                user.major,
                                user.nickname,
                                user.imageUrl
                        )
                ))
                .from(post)
                .leftJoin(user).on(post.postedUserId.eq(user.id))
                .leftJoin(bookmark).on(post.id.eq(bookmark.postId).and(bookmark.userId.eq(userId)))
                .where(post.id.eq(postId));
        PostData result = data.fetchOne();
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<PostData> findPostById(Long postId) {
        QStudyPostEntity post = studyPostEntity;
        QUserEntity user = userEntity;

        JPAQuery<PostData> data = jpaQueryFactory
                .select(Projections.constructor(
                        PostData.class,
                        post.id.as("postId"),
                        post.title,
                        post.createdDate,
                        post.content,
                        post.major,
                        post.studyPerson,
                        post.filteredGender,
                        post.studyWay,
                        post.penalty,
                        post.penaltyWay,
                        post.studyStartDate,
                        post.studyEndDate,
                        post.remainingSeat,
                        Expressions.constant(false),
                        Expressions.constant(false),
                        Projections.constructor(
                                UserData.class,
                                user.id,
                                user.major,
                                user.nickname,
                                user.imageUrl
                        )
                ))
                .from(post)
                .leftJoin(user).on(post.postedUserId.eq(user.id))
                .where(post.id.eq(postId));
        PostData result = data.fetchOne();
        return Optional.ofNullable(result);
    }

    @Override
    public List<RelatedPostData> findByMajor(MajorType major, Long exceptPostId) {
        QStudyPostEntity post = studyPostEntity;
        QUserEntity user = userEntity;
        JPAQuery<RelatedPostData> data = jpaQueryFactory.select(
                        Projections.constructor(RelatedPostData.class,
                                post.id.as("postId"),
                                post.title,
                                post.major,
                                post.remainingSeat,
                                Projections.constructor(
                                        UserData.class,
                                        user.id.as("userId"),
                                        user.major,
                                        user.nickname,
                                        user.imageUrl
                                )
                        )
                )
                .from(post)
                .leftJoin(user).on(post.postedUserId.eq(user.id))
                .where(post.major.eq(major).and(post.id.ne(exceptPostId)))
                .orderBy(
                        post.remainingSeat.asc(),
                        post.createdDate.desc()
                )
                .limit(5);

        List<RelatedPostData> result = data.fetch();

        return result;
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
