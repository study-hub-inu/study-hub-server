package kr.co.studyhubinu.studyhubserver.userstudy.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserStudyEntity is a Querydsl query type for UserStudyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserStudyEntity extends EntityPathBase<UserStudyEntity> {

    private static final long serialVersionUID = 761435169L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserStudyEntity userStudyEntity = new QUserStudyEntity("userStudyEntity");

    public final BooleanPath approve = createBoolean("approve");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final kr.co.studyhubinu.studyhubserver.study.domain.QStudyEntity study;

    public final kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity user;

    public QUserStudyEntity(String variable) {
        this(UserStudyEntity.class, forVariable(variable), INITS);
    }

    public QUserStudyEntity(Path<? extends UserStudyEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserStudyEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserStudyEntity(PathMetadata metadata, PathInits inits) {
        this(UserStudyEntity.class, metadata, inits);
    }

    public QUserStudyEntity(Class<? extends UserStudyEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.study = inits.isInitialized("study") ? new kr.co.studyhubinu.studyhubserver.study.domain.QStudyEntity(forProperty("study")) : null;
        this.user = inits.isInitialized("user") ? new kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity(forProperty("user")) : null;
    }

}

