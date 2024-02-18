package kr.co.studyhubinu.studyhubserver.reject.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRejectEntity is a Querydsl query type for RejectEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRejectEntity extends EntityPathBase<RejectEntity> {

    private static final long serialVersionUID = 147312695L;

    public static final QRejectEntity rejectEntity = new QRejectEntity("rejectEntity");

    public final kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity _super = new kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> rejectedUserId = createNumber("rejectedUserId", Long.class);

    public final StringPath rejectReason = createString("rejectReason");

    public final NumberPath<Long> studyId = createNumber("studyId", Long.class);

    public QRejectEntity(String variable) {
        super(RejectEntity.class, forVariable(variable));
    }

    public QRejectEntity(Path<? extends RejectEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRejectEntity(PathMetadata metadata) {
        super(RejectEntity.class, metadata);
    }

}

