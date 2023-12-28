package kr.co.studyhubinu.studyhubserver.studypost.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyPostEntity is a Querydsl query type for StudyPostEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyPostEntity extends EntityPathBase<StudyPostEntity> {

    private static final long serialVersionUID = -2147182655L;

    public static final QStudyPostEntity studyPostEntity = new QStudyPostEntity("studyPostEntity");

    public final kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity _super = new kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity(this);

    public final StringPath chatUrl = createString("chatUrl");

    public final BooleanPath close = createBoolean("close");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final EnumPath<kr.co.studyhubinu.studyhubserver.user.enums.GenderType> filteredGender = createEnum("filteredGender", kr.co.studyhubinu.studyhubserver.user.enums.GenderType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<kr.co.studyhubinu.studyhubserver.user.enums.MajorType> major = createEnum("major", kr.co.studyhubinu.studyhubserver.user.enums.MajorType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> penalty = createNumber("penalty", Integer.class);

    public final StringPath penaltyWay = createString("penaltyWay");

    public final NumberPath<Long> postedUserId = createNumber("postedUserId", Long.class);

    public final NumberPath<Integer> remainingSeat = createNumber("remainingSeat", Integer.class);

    public final DatePath<java.time.LocalDate> studyEndDate = createDate("studyEndDate", java.time.LocalDate.class);

    public final NumberPath<Long> studyId = createNumber("studyId", Long.class);

    public final NumberPath<Integer> studyPerson = createNumber("studyPerson", Integer.class);

    public final DatePath<java.time.LocalDate> studyStartDate = createDate("studyStartDate", java.time.LocalDate.class);

    public final EnumPath<kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType> studyWay = createEnum("studyWay", kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType.class);

    public final StringPath title = createString("title");

    public QStudyPostEntity(String variable) {
        super(StudyPostEntity.class, forVariable(variable));
    }

    public QStudyPostEntity(Path<? extends StudyPostEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyPostEntity(PathMetadata metadata) {
        super(StudyPostEntity.class, metadata);
    }

}

