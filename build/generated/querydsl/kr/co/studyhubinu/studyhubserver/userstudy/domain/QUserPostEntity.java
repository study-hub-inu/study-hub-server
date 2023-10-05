package kr.co.studyhubinu.studyhubserver.userstudy.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserPostEntity is a Querydsl query type for UserPostEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPostEntity extends EntityPathBase<UserStudyEntity> {

    private static final long serialVersionUID = -1684592337L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserPostEntity userPostEntity = new QUserPostEntity("userPostEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final kr.co.studyhubinu.studyhubserver.study.domain.QStudyPostEntity studyPostEntity;

    public final kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity user;

    public QUserPostEntity(String variable) {
        this(UserStudyEntity.class, forVariable(variable), INITS);
    }

    public QUserPostEntity(Path<? extends UserStudyEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserPostEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserPostEntity(PathMetadata metadata, PathInits inits) {
        this(UserStudyEntity.class, metadata, inits);
    }

    public QUserPostEntity(Class<? extends UserStudyEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studyPostEntity = inits.isInitialized("studyPostEntity") ? new kr.co.studyhubinu.studyhubserver.study.domain.QStudyPostEntity(forProperty("studyPostEntity")) : null;
        this.user = inits.isInitialized("user") ? new kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity(forProperty("user")) : null;
    }

}

