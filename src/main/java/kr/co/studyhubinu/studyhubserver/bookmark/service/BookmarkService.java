package kr.co.studyhubinu.studyhubserver.bookmark.service;

import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookmarkEntity;
import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookmarkRepository;
import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final StudyPostRepository studyPostRepository;

    @Transactional
    public boolean doBookMark(Long userId, Long postId) {
        AtomicBoolean created = new AtomicBoolean(false);
        validateUserExist(userId);
        validateStudyPostExist(postId);
        bookmarkRepository.findByUserIdAndPostId(userId, postId).ifPresentOrElse(
                bookMark -> {
                    bookmarkRepository.delete(bookMark);
                    created.set(false);
                },
                () -> {
                    BookmarkEntity bookmark = new BookmarkEntity(postId, userId);
                    bookmarkRepository.save(bookmark);
                    created.set(true);
                }
        );
        return created.get();
    }

    public boolean checkBookmarked(Long userId, Long postId) {
        validateUserExist(userId);
        validateStudyPostExist(postId);
        return bookmarkRepository.findByUserIdAndPostId(userId, postId).isPresent();
    }

    private void validateUserExist(Long userId) {
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private void validateStudyPostExist(Long postId) {
        studyPostRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }

    @Transactional
    public void deleteAllBookmark(Long userId) {
        bookmarkRepository.deleteAllBookmarksByUserId(userId);
    }
}
