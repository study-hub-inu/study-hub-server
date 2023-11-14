package kr.co.studyhubinu.studyhubserver.comment.service;

import kr.co.studyhubinu.studyhubserver.comment.domain.CommentEntity;
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
    private final StudyPostRepository studyPostRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createComment(CreateCommentRequest request, Long userId) {
        studyPostRepository.findById(request.getPostId()).orElseThrow(PostNotFoundException::new);
        UserEntity findUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        CommentEntity comment = request.toEntity(findUser.getId());
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(UpdateCommentRequest request, Long userId) {
        UserEntity findUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        CommentEntity findComment = commentRepository.findById(request.getCommentId()).orElseThrow(CommentNotFoundException::new);
        validIsCommentOfUser(findUser.getId(), findComment);
        findComment.update(request.getContent());
    }

    private void validIsCommentOfUser(Long userId, CommentEntity comment) {
        if (!comment.isCommentOfUser(userId)) throw new UserNotAccessRightException();
    }


    public void deleteComment(Long commentId, Long userId) {
        UserEntity findUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        CommentEntity findComment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        validIsCommentOfUser(findUser.getId(), findComment);
        commentRepository.delete(findComment);
    }
}
