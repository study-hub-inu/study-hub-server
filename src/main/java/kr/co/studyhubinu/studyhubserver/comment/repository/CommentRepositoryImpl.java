package kr.co.studyhubinu.studyhubserver.comment.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.comment.dto.response.CommentResponse;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.studyhubinu.studyhubserver.comment.domain.QCommentEntity.commentEntity;
import static kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommentResponse> findSliceByPostIdWithUserId(Long postId, Long userId, Pageable pageable) {
        return jpaQueryFactory
                .select(Projections.constructor(CommentResponse.class,
                        commentEntity.id,
                        commentEntity.content,
                        commentEntity.createdDate,
                        loginPredicate(userId),
                        Projections.constructor(
                                UserData.class,
                                userEntity.id,
                                userEntity.major,
                                userEntity.nickname,
                                userEntity.imageUrl
                        )

                ))
                .from(commentEntity)
                .leftJoin(userEntity).on(commentEntity.userId.eq(userEntity.id))
                .where(commentEntity.postId.eq(postId))
                .orderBy(commentEntity.createdDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();
    }

    private BooleanExpression loginPredicate(Long userId) {
        if (userId != null) {
            return Expressions.booleanTemplate("{0} = {1}", commentEntity.userId, userId);
        }
        return Expressions.asBoolean(Expressions.constant(false));
    }
}
