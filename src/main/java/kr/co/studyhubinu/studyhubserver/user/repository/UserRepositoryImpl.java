//package kr.co.studyhubinu.studyhubserver.user.repository;
//
//
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.impl.JPAQuery;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import kr.co.studyhubinu.studyhubserver.study.domain.QStudyPostEntity;
//import kr.co.studyhubinu.studyhubserver.study.dto.response.FindPostResponseByAll;
//import kr.co.studyhubinu.studyhubserver.userstudy.domain.QUserPostEntity;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Slice;
//import org.springframework.data.domain.SliceImpl;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//import static kr.co.studyhubinu.studyhubserver.study.domain.QStudyPostEntity.studyPostEntity;
//import static kr.co.studyhubinu.studyhubserver.userstudy.domain.QUserPostEntity.*;
//
//@Repository
//@RequiredArgsConstructor
//public class UserRepositoryImpl implements UserRepositoryCustom {
//
//    private final JPAQueryFactory jpaQueryFactory;
//
//    @Override
//    public Slice<FindPostResponseByAll> findMyPost(Long userId, Pageable pageable) {
//        QStudyPostEntity post = studyPostEntity;
//        QUserPostEntity userPost = userPostEntity;
//
//        JPAQuery<FindPostResponseByAll> studyPostDto = jpaQueryFactory
//                .select(Projections.constructor(FindPostResponseByAll.class,
//                        post.id, post.major, post.title, post.content, post.studyPerson, post.studyPerson, post.close))
//                .from(post)
//                .where(userPost.id.eq(userId), post.id.eq(userPost.id))
//                .orderBy(post.createdDate.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize() + 1);
//
//        return toSlice(pageable, studyPostDto.fetch());
//
//    }
//
//    public static <T> Slice<T> toSlice(final Pageable pageable, final List<T> items) {
//        if (items.size() > pageable.getPageSize()) {
//            items.remove(items.size()-1);
//            return new SliceImpl<>(items, pageable, true);
//        }
//        return new SliceImpl<>(items, pageable, false);
//    }
//}
