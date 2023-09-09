package kr.co.studyhubinu.studyhubserver.bookmark.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBookMarkEntity is a Querydsl query type for BookMarkEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookMarkEntity extends EntityPathBase<BookMarkEntity> {

    private static final long serialVersionUID = 1763927877L;

    public static final QBookMarkEntity bookMarkEntity = new QBookMarkEntity("bookMarkEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QBookMarkEntity(String variable) {
        super(BookMarkEntity.class, forVariable(variable));
    }

    public QBookMarkEntity(Path<? extends BookMarkEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookMarkEntity(PathMetadata metadata) {
        super(BookMarkEntity.class, metadata);
    }

}

