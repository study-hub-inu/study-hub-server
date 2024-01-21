package kr.co.studyhubinu.studyhubserver.apply.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QApplyEntity is a Querydsl query type for ApplyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplyEntity extends EntityPathBase<ApplyEntity> {

    private static final long serialVersionUID = -1543003327L;

    public static final QApplyEntity applyEntity = new QApplyEntity("applyEntity");

    public final kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity _super = new kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<kr.co.studyhubinu.studyhubserver.apply.enums.Inspection> inspection = createEnum("inspection", kr.co.studyhubinu.studyhubserver.apply.enums.Inspection.class);

    public final StringPath introduce = createString("introduce");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> studyId = createNumber("studyId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QApplyEntity(String variable) {
        super(ApplyEntity.class, forVariable(variable));
    }

    public QApplyEntity(Path<? extends ApplyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApplyEntity(PathMetadata metadata) {
        super(ApplyEntity.class, metadata);
    }

}

