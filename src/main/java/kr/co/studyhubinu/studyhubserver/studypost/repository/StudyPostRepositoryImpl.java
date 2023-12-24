package kr.co.studyhubinu.studyhubserver.studypost.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.bookmark.domain.QBookmarkEntity;
import kr.co.studyhubinu.studyhubserver.studypost.domain.QStudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetBookmarkedPostsData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.RelatedPostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.InquiryRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByInquiry;
import kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserData;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static kr.co.studyhubinu.studyhubserver.bookmark.domain.QBookmarkEntity.bookmarkEntity;
import static kr.co.studyhubinu.studyhubserver.studypost.domain.QStudyPostEntity.studyPostEntity;
import static kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class StudyPostRepositoryImpl implements StudyPostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<FindPostResponseByInquiry> findByInquiry(final InquiryRequest inquiryRequest, final Pageable pageable, Long userId) {
        QStudyPostEntity post = studyPostEntity;
        QUserEntity user = userEntity;
        QBookmarkEntity bookmark = bookmarkEntity;

        JPAQuery<FindPostResponseByInquiry> data = jpaQueryFactory
                .select(Projections.constructor(FindPostResponseByInquiry.class,
                        post.id.as("postId"), post.major, post.title, post.studyStartDate, post.studyEndDate,
                        post.createdDate, post.studyPerson, post.filteredGender,
                        post.penalty, post.penaltyWay, post.remainingSeat, post.close,
                        bookmarkPredicate(userId, bookmark),
                        Projections.constructor(
                                UserData.class,
                                user.id, user.major, user.nickname, user.imageUrl
                        )
                ))
                .from(post)
                .leftJoin(user).on(post.postedUserId.eq(user.id))
                .where(wherePredicate(post, inquiryRequest))
                .orderBy(hotPredicate(post, inquiryRequest))
                .orderBy(post.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);

        if (userId != null) {
            data.leftJoin(bookmark).on(post.id.eq(bookmark.postId).and(bookmark.userId.eq(userId)));
        }

        return toSlice(pageable, data.fetch());
    }

    private Predicate wherePredicate(QStudyPostEntity post, InquiryRequest inquiryRequest) {
        BooleanBuilder predicate = new BooleanBuilder();

        if(inquiryRequest.getInquiryText() != null) {
            addMajorPredicate(post, predicate, inquiryRequest.getInquiryText());
            addTitlePredicate(post, predicate, inquiryRequest);
        }
        return predicate.getValue();
    }

    private void addMajorPredicate(QStudyPostEntity post, BooleanBuilder predicate, String inquiryText) {
        predicate.and(post.major.eq(MajorType.of(inquiryText)));
    }

    private void addTitlePredicate(QStudyPostEntity post, BooleanBuilder predicate, InquiryRequest inquiryRequest) {
        if(inquiryRequest.isTitleAndMajor()) {
            predicate.or(post.title.contains(inquiryRequest.getInquiryText()));
        }
    }

    private OrderSpecifier<?> hotPredicate(QStudyPostEntity post, InquiryRequest inquiryRequest) {
        if(inquiryRequest.isHot()) {
            return post.remainingSeat.asc();
        }
        return post.createdDate.desc();
    }

    private Predicate bookmarkPredicate(Long userId, QBookmarkEntity bookmark) {
        if(userId != null) {
            return Expressions.booleanTemplate("{0} = {1}", bookmark.userId, userId);
        }
        return Expressions.asBoolean(Expressions.constant(false));
    }

    @Override
    public Slice<GetBookmarkedPostsData> findPostsByBookmarked(Long userId, Pageable pageable) {
        QStudyPostEntity post = studyPostEntity;
        QBookmarkEntity bookmark = bookmarkEntity;

        JPAQuery<GetBookmarkedPostsData> studyPostDto = jpaQueryFactory.select(
                        Projections.constructor(GetBookmarkedPostsData.class,
                                post.id.as("postId"), post.major, post.title, post.content, post.remainingSeat, post.close))
                .from(post)
                .innerJoin(bookmark)
                .on(bookmark.postId.eq(post.id))
                .where(bookmark.userId.eq(userId))
                .orderBy(post.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);

        return toSlice(pageable, studyPostDto.fetch());
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
                        post.studyPerson, post.filteredGender, post.studyWay, post.penalty,
                        post.penaltyWay, post.studyStartDate, post.studyEndDate, post.chatUrl, post.remainingSeat,
                        userId != null ? Expressions.booleanTemplate("{0} = {1}", post.postedUserId, userId) : Expressions.constant(false),
                        userId != null ? Expressions.booleanTemplate("{0} = {1}", bookmark.userId, userId) : Expressions.constant(false),
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
                        post.remainingSeat.asc(),
                        post.createdDate.desc()
                )
                .limit(postRecommendCount)
                .fetch();
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

    public static <T> Slice<T> toSlice(final Pageable pageable, final List<T> items) {
        if (items.size() > pageable.getPageSize()) {
            items.remove(items.size() - 1);
            return new SliceImpl<>(items, pageable, true);
        }
        return new SliceImpl<>(items, pageable, false);
    }
}
