package kr.co.studyhubinu.studyhubserver.bookmark.service;

import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookMarkEntity;
import kr.co.studyhubinu.studyhubserver.bookmark.dto.request.CreateBookMarkRequest;
import kr.co.studyhubinu.studyhubserver.bookmark.dto.request.FindBookMarkRequest;
import kr.co.studyhubinu.studyhubserver.bookmark.dto.response.FindBookMarkResponse;
import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookMarkRepository;
import kr.co.studyhubinu.studyhubserver.exception.bookmark.BookMarkNotFoundException;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyPostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class BookMarkService {

    private final BookMarkRepository bookMarkRepository;

    public void saveBookMark(CreateBookMarkRequest request) {
        bookMarkRepository.save(request.toEntity(request));
    }

    public void deleteBookMark(Long bookMarkId) {
        BookMarkEntity bookMark = bookMarkRepository.findById(bookMarkId).orElseThrow(BookMarkNotFoundException::new);
        bookMarkRepository.delete(bookMark);
    }

    public Slice<FindBookMarkResponse> findBookMark(FindBookMarkRequest request) {
        Slice<StudyPostEntity> postEntities = bookMarkRepository.findPostByBookMark(request.getUserId());

        Slice<FindBookMarkResponse> responses = (Slice<FindBookMarkResponse>) postEntities.stream()
                .map(postEntity -> {
                    FindBookMarkResponse response = new FindBookMarkResponse(postEntity.getId(), postEntity.getTitle(), postEntity.getContent(), postEntity.getStudyPerson());
                    return response;
                })
                .collect(Collectors.toList());
        return responses;
    }

}
