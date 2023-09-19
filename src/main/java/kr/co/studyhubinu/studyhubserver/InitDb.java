package kr.co.studyhubinu.studyhubserver;

import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookMarkEntity;
import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookMarkRepository;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.study.enums.StudyWayType;
import kr.co.studyhubinu.studyhubserver.study.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
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
        private final StudyPostRepository studyPostRepository;
        private final BookMarkRepository bookMarkRepository;

        public void userInit() {
            UserEntity user = new UserEntity(1L, "xxx@inu.ac.kr", "asd123", "lee", "www.asdasdas" ,MajorType.COMPUTER_SCIENCE_ENGINEERING, GenderType.FEMALE);
            userRepository.save(user);
        }

        public void postInit() {
            UserEntity user = userRepository.findByEmail("xxx@inu.ac.kr").orElseThrow();

            StudyPostEntity post1 = new StudyPostEntity("정처기 구함", "고수들만", "www.liljay.com",
                    MajorType.COMPUTER_SCIENCE_ENGINEERING, 10, GenderType.MALE, StudyWayType.CONTACT,
                    1000, LocalDate.now(), LocalDate.now(), 1L, 10);
            StudyPostEntity post2 = new StudyPostEntity("축구하실분 구함", "브라질 사람만", "www.liljay.com",
                    MajorType.COMPUTER_SCIENCE_ENGINEERING, 10, GenderType.MALE, StudyWayType.CONTACT,
                    1000, LocalDate.now(), LocalDate.now(), 1L, 10);
            StudyPostEntity post3 = new StudyPostEntity("스파링 뜨실분", "러시아 사람만", "www.liljay.com",
                    MajorType.COMPUTER_SCIENCE_ENGINEERING, 10, GenderType.MALE, StudyWayType.CONTACT,
                    1000, LocalDate.now(), LocalDate.now(), 1L, 10);
            StudyPostEntity post4 = new StudyPostEntity("축구 할놈", "아르헨티나 사람만", "www.liljay.com",
                    MajorType.COMPUTER_SCIENCE_ENGINEERING, 10, GenderType.MALE, StudyWayType.CONTACT,
                    1000, LocalDate.now(), LocalDate.now(), 1L, 10);

            studyPostRepository.save(post1);
            studyPostRepository.save(post2);
            studyPostRepository.save(post3);
            studyPostRepository.save(post4);
        }

        public void bookMarkInit() {
            UserEntity user = userRepository.findByEmail("xxx@inu.ac.kr").orElseThrow();
            StudyPostEntity post1 = studyPostRepository.findById(2L).orElseThrow();

            BookMarkEntity bookMark = new BookMarkEntity(user.getId(), post1.getId());
            bookMarkRepository.save(bookMark);
        }
    }
}
