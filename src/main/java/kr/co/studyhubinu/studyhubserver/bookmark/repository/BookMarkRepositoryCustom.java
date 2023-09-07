package kr.co.studyhubinu.studyhubserver.bookmark.repository;

import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import org.springframework.data.domain.Slice;

public interface BookMarkRepositoryCustom {

    Slice<StudyPostEntity> findPostByBookMark(Long userId);
}
