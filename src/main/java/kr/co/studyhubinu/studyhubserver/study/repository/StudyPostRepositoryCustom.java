package kr.co.studyhubinu.studyhubserver.study.repository;

import kr.co.studyhubinu.studyhubserver.study.dto.response.FindPostResponseByAll;
import kr.co.studyhubinu.studyhubserver.study.dto.response.FindPostResponseByContent;
import kr.co.studyhubinu.studyhubserver.study.dto.response.FindPostResponseByMajor;
import kr.co.studyhubinu.studyhubserver.study.dto.response.FindPostResponseByString;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;


public interface StudyPostRepositoryCustom {

    Slice<FindPostResponseByString> findByTitle(String title, Pageable pageable);

    Slice<FindPostResponseByMajor> findByMajor(MajorType major, Pageable pageable);

    Slice<FindPostResponseByAll> findByAll(Pageable pageable);

    Slice<FindPostResponseByContent> findByContent(String content, Pageable pageable);
}
