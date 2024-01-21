package kr.co.studyhubinu.studyhubserver.user.domain;

import kr.co.studyhubinu.studyhubserver.alarm.repository.AlarmRepository;
import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import kr.co.studyhubinu.studyhubserver.apply.repository.ApplyRepository;

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
    private final ApplyRepository applyRepository;

    public void deleteUserRelatedData(UserEntity user) {
        deleteStudyPost(user);
        deleteBookmark(user);
        deleteUser(user);
        deleteApply(user);
    }

    private void deleteApply(UserEntity user) {
        List<ApplyEntity> apply = applyRepository.findByUserId(user.getId());
        applyRepository.deleteAll(apply);
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
