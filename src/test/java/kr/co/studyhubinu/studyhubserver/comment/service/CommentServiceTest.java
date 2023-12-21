package kr.co.studyhubinu.studyhubserver.comment.service;

import kr.co.studyhubinu.studyhubserver.comment.repository.CommentRepository;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private StudyPostRepository studyPostRepository;

    @InjectMocks
    private CommentService commentService;

}
