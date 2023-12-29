package kr.co.studyhubinu.studyhubserver.notification.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotificationEntity is a Querydsl query type for NotificationEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotificationEntity extends EntityPathBase<NotificationEntity> {

    private static final long serialVersionUID = -1330078833L;

    public static final QNotificationEntity notificationEntity = new QNotificationEntity("notificationEntity");

    public final kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity _super = new kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity(this);

    public final BooleanPath checked = createBoolean("checked");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final EnumPath<kr.co.studyhubinu.studyhubserver.notification.enums.NotificationType> notificationType = createEnum("notificationType", kr.co.studyhubinu.studyhubserver.notification.enums.NotificationType.class);

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final NumberPath<Long> receiverId = createNumber("receiverId", Long.class);

    public final NumberPath<Long> senderId = createNumber("senderId", Long.class);

    public QNotificationEntity(String variable) {
        super(NotificationEntity.class, forVariable(variable));
    }

    public QNotificationEntity(Path<? extends NotificationEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotificationEntity(PathMetadata metadata) {
        super(NotificationEntity.class, metadata);
    }

}

