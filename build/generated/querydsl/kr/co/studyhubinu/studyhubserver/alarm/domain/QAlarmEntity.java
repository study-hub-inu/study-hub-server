package kr.co.studyhubinu.studyhubserver.alarm.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAlarmEntity is a Querydsl query type for AlarmEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlarmEntity extends EntityPathBase<AlarmEntity> {

    private static final long serialVersionUID = 2092893217L;

    public static final QAlarmEntity alarmEntity = new QAlarmEntity("alarmEntity");

    public final kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity _super = new kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity(this);

    public final EnumPath<kr.co.studyhubinu.studyhubserver.alarm.enums.AlarmType> alarmCategory = createEnum("alarmCategory", kr.co.studyhubinu.studyhubserver.alarm.enums.AlarmType.class);

    public final BooleanPath checked = createBoolean("checked");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QAlarmEntity(String variable) {
        super(AlarmEntity.class, forVariable(variable));
    }

    public QAlarmEntity(Path<? extends AlarmEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAlarmEntity(PathMetadata metadata) {
        super(AlarmEntity.class, metadata);
    }

}

