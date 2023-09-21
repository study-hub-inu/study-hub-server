package kr.co.studyhubinu.studyhubserver.bookmark.service;

import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookMarkEntity;
import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookMarkRepository;
import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.study.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookMarkService {

    private final BookMarkRepository bookMarkRepository;
    private final UserRepository userRepository;
    private final StudyPostRepository studyPostRepository;

    @Transactional
    public boolean doBookMark(Long userId, Long postId) {
        AtomicBoolean created = new AtomicBoolean(false);
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        studyPostRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        bookMarkRepository.findByUserIdAndPostId(userId, postId).ifPresentOrElse(
                bookMark -> {
                    bookMarkRepository.delete(bookMark);
                    created.set(false);
                },
                () -> {
                    BookMarkEntity bookmark = new BookMarkEntity(postId, userId);
                    bookMarkRepository.save(bookmark);
                    created.set(true);
                }
        );
        return created.get();
    }

    public boolean checkBookmarked(Long userId, Long postId) {
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        studyPostRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        return bookMarkRepository.findByUserIdAndPostId(userId, postId).isPresent();
    }
}
