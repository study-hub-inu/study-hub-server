package kr.co.studyhubinu.studyhubserver.studypost.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.bookmark.domain.QBookmarkEntity;
import kr.co.studyhubinu.studyhubserver.studypost.domain.QStudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.*;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.InquiryRequest;
import kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserData;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static kr.co.studyhubinu.studyhubserver.bookmark.domain.QBookmarkEntity.bookmarkEntity;
import static kr.co.studyhubinu.studyhubserver.studypost.domain.QStudyPostEntity.studyPostEntity;
import static kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
@Slf4j
public class StudyPostRepositoryImpl implements StudyPostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PostDataByInquiry> findByInquiry(final InquiryRequest inquiryRequest, final Pageable pageable, Long userId) {
        JPAQuery<PostDataByInquiry> data = jpaQueryFactory
                .select(Projections.constructor(PostDataByInquiry.class,
                        studyPostEntity.id.as("postId"), studyPostEntity.major, studyPostEntity.title, studyPostEntity.studyStartDate, studyPostEntity.studyEndDate,
                        studyPostEntity.createdDate, studyPostEntity.studyPerson, studyPostEntity.filteredGender,
                        studyPostEntity.penalty, studyPostEntity.penaltyWay, studyPostEntity.remainingSeat, studyPostEntity.close,
                        bookmarkPredicate(userId),
                        Projections.constructor(
                                UserData.class,
                                userEntity.id, userEntity.major, userEntity.nickname, userEntity.imageUrl
                        )
                ))
                .from(studyPostEntity)
                .leftJoin(userEntity).on(studyPostEntity.postedUserId.eq(userEntity.id))
                .where(textEq(inquiryRequest.getInquiryText()).or(majorEq(inquiryRequest.getInquiryText(), inquiryRequest.isTitleAndMajor())))
                .orderBy(hotPredicate(inquiryRequest), studyPostEntity.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);

        if (userId != null) {
            data.leftJoin(bookmarkEntity).on(studyPostEntity.id.eq(bookmarkEntity.postId).and(bookmarkEntity.userId.eq(userId)));
        }

        return data.fetch();
    }

    @Override
    public List<PostDataByBookmark> findPostsByBookmarked(Long userId, Pageable pageable) {
        QStudyPostEntity post = studyPostEntity;
        QBookmarkEntity bookmark = bookmarkEntity;

        JPAQuery<PostDataByBookmark> studyPostDto = jpaQueryFactory.select(
                        Projections.constructor(PostDataByBookmark.class,
                                post.id.as("postId"), post.major, post.title, post.content, post.remainingSeat, post.close, post.studyId))
                .from(post)
                .innerJoin(bookmark).on(bookmark.postId.eq(post.id))
                .where(bookmark.userId.eq(userId))
                .orderBy(post.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);

        return studyPostDto.fetch();
    }

    @Override
    public List<PostDataByUserId> findByPostedUserId(final Long userId, final Pageable pageable) {
        QStudyPostEntity post = studyPostEntity;
        JPAQuery<PostDataByUserId> data = jpaQueryFactory.select(
                        Projections.constructor(PostDataByUserId.class,
                                post.id.as("postId"),
                                post.major,
                                post.title,
                                post.content,
                                post.remainingSeat,
                                post.close,
                                post.studyId
                        )
                )
                .from(post)
                .where(post.postedUserId.eq(userId))
                .orderBy(post.close.asc(), post.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);


        return data.fetch();
    }

    @Override
    public Optional<PostData> findPostById(Long postId, Long userId) {
        QStudyPostEntity post = studyPostEntity;
        QUserEntity user = userEntity;
        QBookmarkEntity bookmark = bookmarkEntity;

        JPAQuery<PostData> data = jpaQueryFactory
                .select(Projections.constructor(
                        PostData.class,
                        post.id.as("postId"), post.title, post.createdDate, post.content, post.major,
                        post.studyPerson, post.filteredGender, post.studyWay, post.penalty, post.penaltyWay,
                        post.studyStartDate, post.studyEndDate, post.chatUrl, post.remainingSeat,
                        userId != null ? Expressions.booleanTemplate("{0} = {1}", post.postedUserId, userId) : Expressions.constant(false),
                        userId != null ? Expressions.booleanTemplate("{0} = {1}", bookmark.userId, userId) : Expressions.constant(false),
                        post.studyId, post.close.as("isClose"),
                        Projections.constructor(
                                UserData.class,
                                user.id, user.major, user.nickname, user.imageUrl
                        )
                ))
                .from(post)
                .leftJoin(user).on(post.postedUserId.eq(user.id));

        if (userId != null) {
            data.leftJoin(bookmark).on(post.id.eq(bookmark.postId).and(bookmark.userId.eq(userId)));
        }

        PostData result = data.where(post.id.eq(postId)).fetchOne();
        return Optional.ofNullable(result);
    }

    @Override
    public List<String> findPostsByTitleStartWith(String keyword, int postRecommendCount) {
        QStudyPostEntity post = studyPostEntity;
        return jpaQueryFactory.select(post.title)
                .from(post)
                .where(post.title.startsWith(keyword))
                .orderBy(
                        post.remainingSeat.min().asc(),
                        post.createdDate.max().desc()
                )
                .groupBy(studyPostEntity.title)
                .limit(postRecommendCount)
                .fetch();
    }


    @Override
    public List<PostDataByMajor> findByMajor(MajorType major, Long exceptPostId) {
        QStudyPostEntity post = studyPostEntity;
        QUserEntity user = userEntity;
        JPAQuery<PostDataByMajor> data = jpaQueryFactory.select(
                        Projections.constructor(PostDataByMajor.class,
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

        return data.fetch();
    }

    private BooleanExpression textEq(String inquiryText) {
        return studyPostEntity.title.contains(Objects.requireNonNullElse(inquiryText, ""));
    }

    private Predicate majorEq(String inquiryText, boolean titleAndMajor) {
        if (inquiryText != null && titleAndMajor) {
            return studyPostEntity.major.eq(MajorType.of(inquiryText));
        }
        return null;
    }

    private OrderSpecifier<?> hotPredicate(InquiryRequest inquiryRequest) {
        if(inquiryRequest.isHot()) {
            return studyPostEntity.remainingSeat.asc();
        }
        return studyPostEntity.createdDate.desc();
    }

    private BooleanExpression bookmarkPredicate(Long userId) {
        if (userId != null) {
            return Expressions.booleanTemplate("{0} = {1}", bookmarkEntity.userId, userId);
        }
        return Expressions.asBoolean(Expressions.constant(false));
    }
}
