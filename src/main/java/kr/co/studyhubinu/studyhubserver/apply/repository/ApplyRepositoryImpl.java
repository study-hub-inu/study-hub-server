package kr.co.studyhubinu.studyhubserver.apply.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.ApplyUserData;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.ParticipateApplyData;
import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.studyhubinu.studyhubserver.study.domain.QStudyEntity.studyEntity;
import static kr.co.studyhubinu.studyhubserver.studypost.domain.QStudyPostEntity.studyPostEntity;
import static kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity.userEntity;
import static kr.co.studyhubinu.studyhubserver.apply.domain.QApplyEntity.applyEntity;

@Repository
@RequiredArgsConstructor
public class ApplyRepositoryImpl implements ApplyRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ApplyUserData> findByStudy(Long studyId, final Pageable pageable) {
        return jpaQueryFactory
                .select(Projections.constructor(ApplyUserData.class,
                        userEntity.id, userEntity.nickname, userEntity.major, userEntity.imageUrl,
                        applyEntity.introduce, applyEntity.createdDate, applyEntity.inspection))
                .from(applyEntity)
                .innerJoin(userEntity).on(applyEntity.userId.eq(userEntity.id))
                .where(applyEntity.studyId.eq(studyId))
                .orderBy(applyEntity.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();
    }

    @Override
    public List<ParticipateApplyData> findByUserIdAndInspection(Long userId, Pageable pageable) {
            return jpaQueryFactory
                    .select(Projections.constructor(ParticipateApplyData.class,
                            studyEntity.major, studyEntity.title, studyEntity.content, studyEntity.chatUrl,
                            applyEntity.inspection, studyPostEntity.id.as("postId")))
                    .from(applyEntity)
                    .innerJoin(studyEntity).on(applyEntity.studyId.eq(studyEntity.id))
                    .innerJoin(studyPostEntity).on(studyEntity.id.eq(studyPostEntity.studyId))
                    .where(
                            applyEntity.userId.eq(userId)
                                    .and(applyEntity.inspection.eq(Inspection.ACCEPT))
                    )
                    .orderBy(applyEntity.createdDate.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize() + 1)
                    .fetch();
    }
}
