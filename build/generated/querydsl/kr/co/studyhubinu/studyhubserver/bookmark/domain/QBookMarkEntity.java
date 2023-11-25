package kr.co.studyhubinu.studyhubserver.bookmark.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBookmarkEntity is a Querydsl query type for BookmarkEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookmarkEntity extends EntityPathBase<BookmarkEntity> {

    private static final long serialVersionUID = -229536987L;

    public static final QBookmarkEntity bookmarkEntity = new QBookmarkEntity("bookmarkEntity");

    public final kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity _super = new kr.co.studyhubinu.studyhubserver.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QBookmarkEntity(String variable) {
        super(BookmarkEntity.class, forVariable(variable));
    }

    public QBookmarkEntity(Path<? extends BookmarkEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookmarkEntity(PathMetadata metadata) {
        super(BookmarkEntity.class, metadata);
    }

}

