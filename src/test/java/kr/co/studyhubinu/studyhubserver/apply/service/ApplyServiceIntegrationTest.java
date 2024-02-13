package kr.co.studyhubinu.studyhubserver.apply.service;

import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.RequestApplyData;
import kr.co.studyhubinu.studyhubserver.apply.dto.response.FindMyRequestApplyResponse;
import kr.co.studyhubinu.studyhubserver.apply.enums.Inspection;
import kr.co.studyhubinu.studyhubserver.apply.repository.ApplyRepository;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.study.repository.StudyRepository;
import kr.co.studyhubinu.studyhubserver.support.fixture.StudyEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.fixture.UserEntityFixture;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
//public class ApplyServiceIntegrationTest {
//
//    @Autowired
//    ApplyService applyService;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    ApplyRepository applyRepository;
//
//    @Autowired
//    StudyRepository studyRepository;
//
//    @Test
//    void 내가_신청한_스터디목록_조회() {
//        UserEntity user = UserEntityFixture.YEONGJAE.UserEntity_생성();
//        StudyEntity study = studyRepository.save(StudyEntityFixture.INU.studyEntity_생성());
//        userRepository.save(user);
//        applyRepository.save(ApplyEntity.builder()
//                .userId(user.getId()).introduce("안녕")
//                .studyId(study.getId())
//                .inspection(Inspection.ACCEPT)
//                .build());
//
//
//        FindMyRequestApplyResponse applyResponse = applyService.getMyRequestApply(new UserId(user.getId()), 0, 3);
//        RequestApplyData applyData = applyResponse.getRequestStudyData().getContent().get(0);
//
//        assertThat(applyData.getInspection()).isEqualTo(Inspection.ACCEPT);
//        assertThat(applyData.getIntroduce()).isEqualTo("안녕");
//
//    }
//}
