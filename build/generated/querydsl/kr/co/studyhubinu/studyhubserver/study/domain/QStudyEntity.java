package kr.co.studyhubinu.studyhubserver.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyEntity is a Querydsl query type for StudyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyEntity extends EntityPathBase<StudyEntity> {

    private static final long serialVersionUID = -1337579743L;

    public static final QStudyEntity studyEntity = new QStudyEntity("studyEntity");

    public final StringPath chatUrl = createString("chatUrl");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> studyEndDate = createDate("studyEndDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> studyStartDate = createDate("studyStartDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final ListPath<kr.co.studyhubinu.studyhubserver.userstudy.domain.UserStudyEntity, kr.co.studyhubinu.studyhubserver.userstudy.domain.QUserStudyEntity> userStudyEntityList = this.<kr.co.studyhubinu.studyhubserver.userstudy.domain.UserStudyEntity, kr.co.studyhubinu.studyhubserver.userstudy.domain.QUserStudyEntity>createList("userStudyEntityList", kr.co.studyhubinu.studyhubserver.userstudy.domain.UserStudyEntity.class, kr.co.studyhubinu.studyhubserver.userstudy.domain.QUserStudyEntity.class, PathInits.DIRECT2);

    public QStudyEntity(String variable) {
        super(StudyEntity.class, forVariable(variable));
    }

    public QStudyEntity(Path<? extends StudyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyEntity(PathMetadata metadata) {
        super(StudyEntity.class, metadata);
    }

}

