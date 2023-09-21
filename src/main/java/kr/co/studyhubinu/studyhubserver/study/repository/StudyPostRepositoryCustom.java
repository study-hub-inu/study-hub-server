package kr.co.studyhubinu.studyhubserver.study.repository;

import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.study.dto.response.*;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;


public interface StudyPostRepositoryCustom {

    Slice<FindPostResponseByString> findByString(String title, MajorType majorType, String content, Pageable pageable);

    Slice<FindPostResponseByAll> findByAll(Pageable pageable);

    //Slice<StudyPostEntity> findByBookMark(Pageable pageable);
}
