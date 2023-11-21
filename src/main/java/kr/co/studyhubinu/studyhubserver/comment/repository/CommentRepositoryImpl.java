package kr.co.studyhubinu.studyhubserver.comment.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.comment.domain.QCommentEntity;
import kr.co.studyhubinu.studyhubserver.comment.dto.response.CommentResponse;
import kr.co.studyhubinu.studyhubserver.studypost.domain.QStudyPostEntity;
import kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.studyhubinu.studyhubserver.comment.domain.QCommentEntity.commentEntity;
import static kr.co.studyhubinu.studyhubserver.studypost.domain.QStudyPostEntity.studyPostEntity;
import static kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Slice<CommentResponse> findSliceByPostIdWithUserId(Long postId, Long userId, Pageable pageable) {
        QStudyPostEntity post = studyPostEntity;
        QCommentEntity comment = commentEntity;
        QUserEntity user = userEntity;

        JPAQuery<CommentResponse> data = jpaQueryFactory
                .select(Projections.constructor(CommentResponse.class,
                        comment.id,
                        comment.content,
                        comment.createdDate,
                        userId != null ? Expressions.booleanTemplate("{0} = {1}", comment.userId, userId) : Expressions.constant(false),
                        Projections.constructor(
                                UserData.class,
                                user.id,
                                user.major,
                                user.nickname,
                                user.imageUrl
                        )

                ))
                .from(comment)
                .leftJoin(user).on(comment.userId.eq(user.id))
                .orderBy(comment.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);
        return toSlice(pageable, data.fetch());
    }

    public static <T> Slice<T> toSlice(final Pageable pageable, final List<T> items) {
        if (items.size() > pageable.getPageSize()) {
            items.remove(items.size() - 1);
            return new SliceImpl<>(items, pageable, true);
        }
        return new SliceImpl<>(items, pageable, false);
    }
}
