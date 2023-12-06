package kr.co.studyhubinu.studyhubserver.studypost.repository;

import kr.co.studyhubinu.studyhubserver.bookmark.domain.BookmarkEntity;
import kr.co.studyhubinu.studyhubserver.bookmark.repository.BookmarkRepository;
import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetBookmarkedPostsData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.GetMyPostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.PostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.data.RelatedPostData;
import kr.co.studyhubinu.studyhubserver.studypost.dto.request.InquiryRequest;
import kr.co.studyhubinu.studyhubserver.studypost.dto.response.FindPostResponseByInquiry;
import kr.co.studyhubinu.studyhubserver.support.fixture.BookmarkEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.fixture.StudyPostEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.fixture.UserEntityFixture;
import kr.co.studyhubinu.studyhubserver.support.repository.RepositoryTest;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RepositoryTest
@ActiveProfiles("dev")
public class StudyPostRepositoryTest {

    @Autowired
    private StudyPostRepository studyPostRepository;
    @Autowired
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void 유저의_식별자로_게시글_개수를_조회한다() {
        // given
        Long userId1 = 1L;
        Long userId2 = 2L;
        StudyPostEntity post1 = StudyPostEntityFixture.SQLD.studyPostEntity_생성(userId1);
        StudyPostEntity post2 = StudyPostEntityFixture.ENGINEER_INFORMATION_PROCESSING.studyPostEntity_생성(userId1);
        StudyPostEntity post3 = StudyPostEntityFixture.TOEIC.studyPostEntity_생성(userId1);
        studyPostRepository.save(post1);
        studyPostRepository.save(post2);
        studyPostRepository.save(post3);

        // when
        Long expectedCount = studyPostRepository.countByPostedUserId(userId1);
        Long actualCount = studyPostRepository.countByPostedUserId(userId2);

        // then
        assertEquals(expectedCount, 3);
        assertEquals(actualCount, 0);
    }

    @Test
    void 유저의_식별자로_게시글을_최신순으로_조회한다() {
        // given
        Long userId1 = 1L;
        StudyPostEntity post1 = StudyPostEntityFixture.SQLD.studyPostEntity_생성(userId1);
        StudyPostEntity post2 = StudyPostEntityFixture.ENGINEER_INFORMATION_PROCESSING.studyPostEntity_생성(userId1);
        studyPostRepository.save(post1);
        studyPostRepository.save(post2);

        // when
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdDate"));
        Slice<GetMyPostData> expectedResult = studyPostRepository.findSliceByPostedUserId(userId1, pageable);

        // then
        assertThat(expectedResult.getContent()).hasSize(2);
        GetMyPostData data1 = expectedResult.getContent().get(1);
        GetMyPostData data2 = expectedResult.getContent().get(0);
        assertAll(
                () -> assertEquals(post1.getId(), data1.getPostId()),
                () -> assertEquals(post1.getContent(), data1.getContent()),
                () -> assertEquals(post2.getId(), data2.getPostId()),
                () -> assertEquals(post2.getContent(), data2.getContent())
        );
    }

    @Test
    void 유저의_식별자로_북마크된_게시글을_최신순으로_조회한다() {
        // given
        Long postedUserId = 1L;
        Long bookmarkedUserId = 2L;
        StudyPostEntity post1 = StudyPostEntityFixture.SQLD.studyPostEntity_생성(postedUserId);
        StudyPostEntity post2 = StudyPostEntityFixture.ENGINEER_INFORMATION_PROCESSING.studyPostEntity_생성(postedUserId);
        StudyPostEntity post3 = StudyPostEntityFixture.TOEIC.studyPostEntity_생성(postedUserId);
        studyPostRepository.save(post1);
        studyPostRepository.save(post2);
        studyPostRepository.save(post3);
        BookmarkEntity bookmark1 = BookmarkEntityFixture.BOOKMARK_POST1.bookMarkEntity_생성(post1.getId(), bookmarkedUserId);
        bookmarkRepository.save(bookmark1);
        BookmarkEntity bookmark2 = BookmarkEntityFixture.BOOKMARK_POST2.bookMarkEntity_생성(post2.getId(), bookmarkedUserId);
        bookmarkRepository.save(bookmark2);

        // when
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdDate"));
        Slice<GetBookmarkedPostsData> dataSlice = studyPostRepository.findPostsByBookmarked(bookmarkedUserId, pageable);

        // then
        assertThat(dataSlice.getContent()).hasSize(2);
        GetBookmarkedPostsData data1 = dataSlice.getContent().get(1);
        GetBookmarkedPostsData data2 = dataSlice.getContent().get(0);
        assertAll(
                () -> assertEquals(post1.getId(), data1.getPostId()),
                () -> assertEquals(post1.getContent(), data1.getContent()),
                () -> assertEquals(post2.getId(), data2.getPostId()),
                () -> assertEquals(post2.getContent(), data2.getContent())
        );
    }

    @Test
    void 유저의_식별자가_존재하면_게시시글과_유저의_식별자로_상세_조회한다() {
        // given
        Long authUserId = 2L;
        boolean isUsersPost;
        boolean isBookmarked;
        UserEntity user = UserEntityFixture.DONGWOO.UserEntity_생성();
        userRepository.save(user);
        StudyPostEntity post = StudyPostEntityFixture.SQLD.studyPostEntity_생성(user.getId());
        studyPostRepository.save(post);
        BookmarkEntity bookmark = BookmarkEntityFixture.BOOKMARK_POST1.bookMarkEntity_생성(post.getId(), authUserId);
        bookmarkRepository.save(bookmark);
        if (authUserId == user.getId()) {
            isUsersPost = true;
        } else {
            isUsersPost = false;
        }

        if (authUserId == bookmark.getUserId()) {
            isBookmarked = true;
        } else {
            isBookmarked = false;
        }

        // when
        PostData data = studyPostRepository.findPostById(post.getId(), authUserId).orElseThrow(PostNotFoundException::new);

        // then
        assertAll(
                () -> assertEquals(post.getId(), data.getPostId()),
                () -> assertEquals(post.getContent(), data.getContent()),
                () -> assertEquals(isUsersPost, data.isUsersPost()),
                () -> assertEquals(isBookmarked, data.isBookmarked())
        );
    }

    @Test
    void 유저의_식별자가_존재하지_않으면_게시글의_식별자로_상세_조회한다() {
        // given
        Long authUserId = null;
        boolean isUsersPost = false;
        boolean isBookmarked = false;
        UserEntity user = UserEntityFixture.DONGWOO.UserEntity_생성();
        userRepository.save(user);
        StudyPostEntity post = StudyPostEntityFixture.SQLD.studyPostEntity_생성(user.getId());
        studyPostRepository.save(post);
        BookmarkEntity bookmark = BookmarkEntityFixture.BOOKMARK_POST1.bookMarkEntity_생성(post.getId(), user.getId());
        bookmarkRepository.save(bookmark);

        // when
        PostData data = studyPostRepository.findPostById(post.getId(), authUserId).orElseThrow(PostNotFoundException::new);

        // then
        assertAll(
                () -> assertEquals(post.getId(), data.getPostId()),
                () -> assertEquals(post.getContent(), data.getContent()),
                () -> assertEquals(isUsersPost, data.isUsersPost()),
                () -> assertEquals(isBookmarked, data.isBookmarked())
        );
    }

    @Test
    void 관련_전공과_조회에_제외할_게시글_식별자로_게시글을_조회한다() {
        // given
        Long postedUserId = 1L;
        UserEntity relatedPostUser = UserEntityFixture.DONGWOO.UserEntity_생성();
        userRepository.save(relatedPostUser);
        StudyPostEntity mainPost = StudyPostEntityFixture.SQLD.studyPostEntity_생성(postedUserId);
        StudyPostEntity relatedPost = StudyPostEntityFixture.ENGINEER_INFORMATION_PROCESSING.studyPostEntity_생성(relatedPostUser.getId());
        studyPostRepository.save(mainPost);
        studyPostRepository.save(relatedPost);

        // when
        List<RelatedPostData> dataList = studyPostRepository.findByMajor(mainPost.getMajor(), mainPost.getId());
        RelatedPostData data = dataList.get(0);

        // then
        assertAll(
                () -> assertEquals(relatedPost.getId(), data.getPostId()),
                () -> assertEquals(relatedPost.getTitle(), data.getTitle()),
                () -> assertEquals(relatedPostUser.getId(), data.getPostedUser().getUserId()),
                () -> assertEquals(relatedPost.getMajor(), data.getMajor())
        );
    }

    @Test
    void 유저의_식별자가_존재하면_검색어와_인기순_여부로_게시글을_조회한다() {
        // given
        Long authUserId = 2L;
        boolean isBookmarked;
        UserEntity user = UserEntityFixture.DONGWOO.UserEntity_생성();
        userRepository.save(user);
        StudyPostEntity post1 = StudyPostEntityFixture.SQLD.studyPostEntity_생성(user.getId());
        StudyPostEntity post2 = StudyPostEntityFixture.ENGINEER_INFORMATION_PROCESSING.studyPostEntity_생성(user.getId());
        studyPostRepository.save(post1);
        studyPostRepository.save(post2);
        BookmarkEntity bookmark = BookmarkEntityFixture.BOOKMARK_POST1.bookMarkEntity_생성(post1.getId(), authUserId);
        bookmarkRepository.save(bookmark);
        if (authUserId == bookmark.getUserId()) {
            isBookmarked = true;
        } else {
            isBookmarked = false;
        }
        InquiryRequest request = new InquiryRequest("SQLD", true, true);

        // when
        Slice<FindPostResponseByInquiry> dataSlice = studyPostRepository.findByInquiry(request, PageRequest.of(0, 3), authUserId);
        FindPostResponseByInquiry data = dataSlice.getContent().get(0);

        // then
        assertAll(
                () -> assertEquals(post1.getId(), data.getPostId()),
                () -> assertEquals(post1.getTitle(), data.getTitle()),
                () -> assertEquals(isBookmarked, data.isBookmarked()),
                () -> assertEquals(user.getId(), data.getUserData().getUserId())
        );
    }
}
