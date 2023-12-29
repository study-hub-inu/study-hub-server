package kr.co.studyhubinu.studyhubserver.notification.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.notification.domain.QNotificationEntity;
import kr.co.studyhubinu.studyhubserver.notification.dto.response.NotificationResponse;
import kr.co.studyhubinu.studyhubserver.studypost.domain.QStudyPostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.studyhubinu.studyhubserver.notification.domain.QNotificationEntity.notificationEntity;
import static kr.co.studyhubinu.studyhubserver.studypost.domain.QStudyPostEntity.studyPostEntity;


@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<NotificationResponse> findNotificationByuserId(Long userId, Pageable pageable) {
        QNotificationEntity notification = notificationEntity;
        QStudyPostEntity post = studyPostEntity;

        JPAQuery<NotificationResponse> alarmResponseJPAQuery = jpaQueryFactory.select(
                        Projections.constructor(NotificationResponse.class,
                                notification.id.as("alarmId"),
                                notification.postId,
                                notification.userId,
                                notification.notificationType,
                                post.title.as("postTitle"),
                                notification.checked.as("isChecked"),
                                notification.createdDate))
                .from(notification)
                .leftJoin(post)
                .on(notification.postId.eq(post.id))
                .where(notification.userId.eq(userId))
                .orderBy(post.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);

        return toSlice(pageable, alarmResponseJPAQuery.fetch());
    }

    public static <T> Slice<T> toSlice(final Pageable pageable, final List<T> items) {
        if (items.size() > pageable.getPageSize()) {
            items.remove(items.size()-1);
            return new SliceImpl<>(items, pageable, true);
        }
        return new SliceImpl<>(items, pageable, false);
    }
}
