package kr.co.studyhubinu.studyhubserver.comment.service;

import kr.co.studyhubinu.studyhubserver.comment.domain.CommentEntity;
import kr.co.studyhubinu.studyhubserver.comment.domain.CommentValidator;
import kr.co.studyhubinu.studyhubserver.comment.dto.request.CreateCommentRequest;
import kr.co.studyhubinu.studyhubserver.comment.dto.request.UpdateCommentRequest;
import kr.co.studyhubinu.studyhubserver.comment.dto.response.CommentResponse;
import kr.co.studyhubinu.studyhubserver.comment.repository.CommentRepository;
import kr.co.studyhubinu.studyhubserver.exception.comment.CommentNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentValidator commentValidator;
    private final UserRepository userRepository;
    private final StudyPostRepository studyPostRepository;

    @Transactional
    public void createComment(CreateCommentRequest request, Long userId) {
        validateStudyPostExist(request.getPostId());
        validateUserExist(userId);
        CommentEntity comment = request.toEntity(userId);
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(UpdateCommentRequest request, Long userId) {
        validateUserExist(userId);
        CommentEntity findComment = findComment(request.getCommentId());
        commentValidator.validIsCommentOfUser(userId, findComment);
        findComment.update(request.getContent());
    }

    @Transactional
    public void deleteComment(Long commentId, Long userId) {
        validateUserExist(userId);
        CommentEntity findComment = findComment(commentId);
        commentValidator.validIsCommentOfUser(commentId, findComment);
        commentRepository.delete(findComment);
    }

    public Slice<CommentResponse> getComments(Long postId, int page, int size, Long userId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        validateStudyPostExist(postId);
        validateUserExist(userId);
        return commentRepository.findSliceByPostIdWithUserId(postId, userId, pageable);
    }

    private void validateUserExist(Long userId) {
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private void validateStudyPostExist(Long postId) {
        studyPostRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }

    private CommentEntity findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
    }
}
