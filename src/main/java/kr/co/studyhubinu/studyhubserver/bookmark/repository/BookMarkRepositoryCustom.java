package kr.co.studyhubinu.studyhubserver.bookmark.repository;

import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;

import java.util.List;

public interface BookMarkRepositoryCustom {

    List<StudyPostEntity> findPostByBookMark(Long userId);
}
