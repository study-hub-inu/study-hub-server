package kr.co.studyhubinu.studyhubserver.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 228813903L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity _super = new kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final EnumPath<kr.co.studyhubinu.studyhubserver.user.enums.GenderType> gender = createEnum("gender", kr.co.studyhubinu.studyhubserver.user.enums.GenderType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final EnumPath<kr.co.studyhubinu.studyhubserver.user.enums.MajorType> major = createEnum("major", kr.co.studyhubinu.studyhubserver.user.enums.MajorType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final ListPath<kr.co.studyhubinu.studyhubserver.userstudy.domain.UserStudyEntity, kr.co.studyhubinu.studyhubserver.userstudy.domain.QUserStudyEntity> userStudyEntitiyList = this.<kr.co.studyhubinu.studyhubserver.userstudy.domain.UserStudyEntity, kr.co.studyhubinu.studyhubserver.userstudy.domain.QUserStudyEntity>createList("userStudyEntitiyList", kr.co.studyhubinu.studyhubserver.userstudy.domain.UserStudyEntity.class, kr.co.studyhubinu.studyhubserver.userstudy.domain.QUserStudyEntity.class, PathInits.DIRECT2);

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

