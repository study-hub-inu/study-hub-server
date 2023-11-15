package kr.co.studyhubinu.studyhubserver.alarm.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.alarm.domain.QAlarmEntity;
import kr.co.studyhubinu.studyhubserver.alarm.dto.response.AlarmResponse;
import kr.co.studyhubinu.studyhubserver.studypost.domain.QStudyPostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.studyhubinu.studyhubserver.alarm.domain.QAlarmEntity.alarmEntity;
import static kr.co.studyhubinu.studyhubserver.studypost.domain.QStudyPostEntity.studyPostEntity;


@Repository
@RequiredArgsConstructor
public class AlarmRepositoryImpl implements AlarmRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<AlarmResponse> findAlarmByuserId(Long userId, Pageable pageable) {
        QAlarmEntity alarm = alarmEntity;
        QStudyPostEntity post = studyPostEntity;

        JPAQuery<AlarmResponse> alarmResponseJPAQuery = jpaQueryFactory.select(
                        Projections.constructor(AlarmResponse.class,
                                alarm.id.as("alarmId"),
                                alarm.postId,
                                alarm.userId,
                                alarm.alarmCategory,
                                post.title.as("postTitle"),
                                alarm.checked.as("isChecked"),
                                alarm.createdDate))
                .from(alarm)
                .leftJoin(post)
                .on(alarm.postId.eq(post.id))
                .where(alarm.userId.eq(userId))
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
