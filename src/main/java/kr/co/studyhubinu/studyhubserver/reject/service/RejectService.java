package kr.co.studyhubinu.studyhubserver.reject.service;

import kr.co.studyhubinu.studyhubserver.reject.dto.response.RejectReasonResponse;
import kr.co.studyhubinu.studyhubserver.reject.repository.RejectRepository;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RejectService {

    private final RejectRepository rejectRepository;

    public RejectReasonResponse findRejectReason(UserId userId, Long studyId) {
        return rejectRepository.findByStudyIdAndRejectedUserId(userId.getId(), studyId);
    }
}
