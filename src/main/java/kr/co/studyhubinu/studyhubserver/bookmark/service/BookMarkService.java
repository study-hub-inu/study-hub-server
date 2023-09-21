package kr.co.studyhubinu.studyhubserver.bookmark.service;

import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookMarkEntity;
import kr.co.studyhubinu.studyhubserver.bookmark.dto.request.CreateBookMarkRequest;
import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookMarkRepository;
import kr.co.studyhubinu.studyhubserver.exception.bookmark.BookMarkNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookMarkService {

    private final BookMarkRepository bookMarkRepository;

    public void saveBookMark(Long id, CreateBookMarkRequest request) {
        bookMarkRepository.save(BookMarkEntity.builder()
                .userId(id)
                .postId(request.getPostId())
                .build());
    }

    public void deleteBookMark(Long bookMarkId) {
        BookMarkEntity bookMark = bookMarkRepository.findById(bookMarkId).orElseThrow(BookMarkNotFoundException::new);
        bookMarkRepository.delete(bookMark);
    }

}
