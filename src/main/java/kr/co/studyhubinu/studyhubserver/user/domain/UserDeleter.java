package kr.co.studyhubinu.studyhubserver.user.domain;

import kr.co.studyhubinu.studyhubserver.alarm.repository.AlarmRepository;
import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookmarkEntity;
import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookmarkRepository;
import kr.co.studyhubinu.studyhubserver.study.StudyRepository;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDeleter {

    private final UserRepository userRepository;
    private final StudyPostRepository studyPostRepository;
    private final StudyRepository studyRepository;
    private final BookmarkRepository bookMarkRepository;
    private final AlarmRepository alarmRepository;

    public void deleteUserRelatedData(UserEntity user) {
        deleteStudyPost(user);
        deleteBookmark(user);
        deleteUser(user);
    }

    private void deleteUser(UserEntity user) {
        userRepository.delete(user);
    }

    private void deleteBookmark(UserEntity user) {
        List<BookmarkEntity> bookmarks = bookMarkRepository.findByUserId(user.getId());
        bookMarkRepository.deleteAll(bookmarks);
    }

    private void deleteStudyPost(UserEntity user) {
        List<StudyPostEntity> studyPosts = studyPostRepository.findByPostedUserId(user.getId());
        studyPostRepository.deleteAll(studyPosts);
    }
}
