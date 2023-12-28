package kr.co.studyhubinu.studyhubserver.apply.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApplyEntity is a Querydsl query type for ApplyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplyEntity extends EntityPathBase<ApplyEntity> {

    private static final long serialVersionUID = -1543003327L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApplyEntity applyEntity = new QApplyEntity("applyEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final kr.co.studyhubinu.studyhubserver.study.domain.QStudyEntity study;

    public final kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity user;

    public QApplyEntity(String variable) {
        this(ApplyEntity.class, forVariable(variable), INITS);
    }

    public QApplyEntity(Path<? extends ApplyEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApplyEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApplyEntity(PathMetadata metadata, PathInits inits) {
        this(ApplyEntity.class, metadata, inits);
    }

    public QApplyEntity(Class<? extends ApplyEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.study = inits.isInitialized("study") ? new kr.co.studyhubinu.studyhubserver.study.domain.QStudyEntity(forProperty("study")) : null;
        this.user = inits.isInitialized("user") ? new kr.co.studyhubinu.studyhubserver.user.domain.QUserEntity(forProperty("user")) : null;
    }

}

