package kr.co.studyhubinu.studyhubserver.comment.service;

import kr.co.studyhubinu.studyhubserver.comment.domain.CommentEntity;
import kr.co.studyhubinu.studyhubserver.comment.domain.CommentValidator;
import kr.co.studyhubinu.studyhubserver.comment.dto.request.CreateCommentRequest;
import kr.co.studyhubinu.studyhubserver.comment.dto.request.UpdateCommentRequest;
import kr.co.studyhubinu.studyhubserver.comment.repository.CommentRepository;
import kr.co.studyhubinu.studyhubserver.exception.comment.CommentNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentValidator commentValidator;

    @Transactional
    public void createComment(CreateCommentRequest request, Long userId) {
        commentValidator.validPostExist(request.getPostId());
        commentValidator.validUserExist(userId);
        CommentEntity comment = request.toEntity(userId);
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(UpdateCommentRequest request, Long userId) {
        commentValidator.validUserExist(userId);
        CommentEntity findComment = commentValidator.validCommentExist(request.getCommentId());
        commentValidator.validIsCommentOfUser(userId, findComment);
        findComment.update(request.getContent());
    }

    public void deleteComment(Long commentId, Long userId) {
        commentValidator.validUserExist(userId);
        CommentEntity findComment = commentValidator.validCommentExist(commentId);
        commentValidator.validIsCommentOfUser(commentId, findComment);
        commentRepository.delete(findComment);
    }
}
