package kr.co.studyhubinu.studyhubserver.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyPostEntity is a Querydsl query type for StudyPostEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyPostEntity extends EntityPathBase<StudyPostEntity> {

    private static final long serialVersionUID = -444406559L;

    private static final PathInits INITS = PathInits.DIRECT2;

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

    public final DatePath<java.time.LocalDate> studyEndDate = createDate("studyEndDate", java.time.LocalDate.class);

    public final NumberPath<Integer> studyPerson = createNumber("studyPerson", Integer.class);

    public final DatePath<java.time.LocalDate> studyStartDate = createDate("studyStartDate", java.time.LocalDate.class);

    public final EnumPath<kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType> studyWay = createEnum("studyWay", kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType.class);

    public final StringPath title = createString("title");

    public final kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity user;

    public QStudyPostEntity(String variable) {
        this(StudyPostEntity.class, forVariable(variable), INITS);
    }

    public QStudyPostEntity(Path<? extends StudyPostEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyPostEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyPostEntity(PathMetadata metadata, PathInits inits) {
        this(StudyPostEntity.class, metadata, inits);
    }

    public QStudyPostEntity(Class<? extends StudyPostEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity(forProperty("user")) : null;
    }

}

