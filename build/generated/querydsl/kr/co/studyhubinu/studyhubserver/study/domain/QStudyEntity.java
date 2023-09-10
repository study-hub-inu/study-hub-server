package kr.co.studyhubinu.studyhubserver.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyEntity is a Querydsl query type for StudyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyEntity extends EntityPathBase<StudyEntity> {

    private static final long serialVersionUID = -1337579743L;

    public static final QStudyEntity studyEntity = new QStudyEntity("studyEntity");

    public final StringPath chatRoomUrl = createString("chatRoomUrl");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath interest = createString("interest");

    public final StringPath name = createString("name");

    public final DatePath<java.time.LocalDate> studyEndDate = createDate("studyEndDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> studyStartDate = createDate("studyStartDate", java.time.LocalDate.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

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

