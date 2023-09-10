package kr.co.studyhubinu.studyhubserver.study.domain;

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

    private static final long serialVersionUID = 873663913L;

    public static final QAlarmEntity alarmEntity = new QAlarmEntity("alarmEntity");

    public final kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity _super = new kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity(this);

    public final EnumPath<kr.co.studyhubinu.studyhubserver.study.enums.AlarmCategoryType> alarmCategory = createEnum("alarmCategory", kr.co.studyhubinu.studyhubserver.study.enums.AlarmCategoryType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final StringPath title = createString("title");

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

