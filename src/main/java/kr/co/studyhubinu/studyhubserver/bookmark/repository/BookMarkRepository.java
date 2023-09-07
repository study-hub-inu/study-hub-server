package kr.co.studyhubinu.studyhubserver.bookmark.repository;

import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookMarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookMarkRepository extends JpaRepository<BookMarkEntity, Long>, BookMarkRepositoryCustom {

}
