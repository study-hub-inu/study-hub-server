package kr.co.studyhubinu.studyhubserver.notice.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTermsOfUseEntity is a Querydsl query type for TermsOfUseEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTermsOfUseEntity extends EntityPathBase<TermsOfUseEntity> {

    private static final long serialVersionUID = -1621487174L;

    public static final QTermsOfUseEntity termsOfUseEntity = new QTermsOfUseEntity("termsOfUseEntity");

    public final kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity _super = new kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath title = createString("title");

    public final StringPath version = createString("version");

    public QTermsOfUseEntity(String variable) {
        super(TermsOfUseEntity.class, forVariable(variable));
    }

    public QTermsOfUseEntity(Path<? extends TermsOfUseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTermsOfUseEntity(PathMetadata metadata) {
        super(TermsOfUseEntity.class, metadata);
    }

}

