package kr.co.studyhubinu.studyhubserver;

import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookMarkEntity;
import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookMarkRepository;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.study.dto.data.StudyPostInfo;
import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.study.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.study.service.StudyPostService;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.userInit();
        initService.postInit();
        initService.bookMarkInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final UserRepository userRepository;
        private final StudyPostService studyPostService;
        private final BookMarkRepository bookMarkRepository;
        private final StudyPostRepository studyPostRepository;

        public void userInit() {
            UserEntity user = new UserEntity( "xxx@inu.ac.kr", "asd123", "lee", "www.asdasdas" ,MajorType.COMPUTER_SCIENCE_ENGINEERING, GenderType.FEMALE);
            userRepository.save(user);
            log.info("test Transaction : " + user.getId());
        }

        public void postInit() {
            UserEntity user = userRepository.findByEmail("xxx@inu.ac.kr").orElseThrow();

            StudyPostInfo post1 = new StudyPostInfo(user.getId(),"정처기 구함", "고수들만", "www.liljay.com",
                    MajorType.COMPUTER_SCIENCE_ENGINEERING, 10, 1000, GenderType.MALE, StudyWayType.CONTACT,
                     LocalDate.now(), LocalDate.now());
            StudyPostInfo post2 = new StudyPostInfo(user.getId(),"축구하실분 구함", "브라질 사람만", "www.liljay.com",
                    MajorType.COMPUTER_SCIENCE_ENGINEERING, 10, 1000, GenderType.MALE, StudyWayType.CONTACT,
                    LocalDate.now(), LocalDate.now());
            StudyPostInfo post3 = new StudyPostInfo(user.getId(),"스파링 뜨실분", "다게스탄만", "www.liljay.com",
                    MajorType.COMPUTER_SCIENCE_ENGINEERING, 10, 1000, GenderType.MALE, StudyWayType.CONTACT,
                    LocalDate.now(), LocalDate.now());
            StudyPostInfo post4 = new StudyPostInfo(user.getId(),"축구 할놈", "아르헨티나 사람만", "www.liljay.com",
                    MajorType.COMPUTER_SCIENCE_ENGINEERING, 10, 1000, GenderType.MALE, StudyWayType.CONTACT,
                    LocalDate.now(), LocalDate.now());


            studyPostService.createPost(post1);
            studyPostService.createPost(post2);
            studyPostService.createPost(post3);
            studyPostService.createPost(post4);
        }

        public void bookMarkInit() {
            UserEntity user = userRepository.findByEmail("xxx@inu.ac.kr").orElseThrow();
            StudyPostEntity post1 = studyPostRepository.findById(2L).orElseThrow();

            BookMarkEntity bookMark = new BookMarkEntity(user.getId(), post1.getId());
            bookMarkRepository.save(bookMark);
        }
    }
}
