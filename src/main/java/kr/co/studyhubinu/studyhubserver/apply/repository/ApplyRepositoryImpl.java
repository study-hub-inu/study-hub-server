package kr.co.studyhubinu.studyhubserver.apply.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.ApplyUserData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                        applyEntity.introduce, applyEntity.createdDate))
                .from(applyEntity)
                .leftJoin(userEntity).on(applyEntity.userId.eq(userEntity.id))
                .where(applyEntity.studyId.eq(studyId))
                .orderBy(applyEntity.createdDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();
    }
}
