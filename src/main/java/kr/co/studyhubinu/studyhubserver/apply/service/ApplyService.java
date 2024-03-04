package kr.co.studyhubinu.studyhubserver.apply.service;

import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import kr.co.studyhubinu.studyhubserver.apply.domain.implementations.ApplyWriter;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.ApplyUserData;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.ParticipateApplyData;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.RejectApplyUserData;
import kr.co.studyhubinu.studyhubserver.apply.dto.data.RequestApplyData;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.AcceptApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.EnrollApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.FindApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.request.RejectApplyRequest;
import kr.co.studyhubinu.studyhubserver.apply.dto.response.FindApplyResponse;
import kr.co.studyhubinu.studyhubserver.apply.dto.response.FindMyRequestApplyResponse;
import kr.co.studyhubinu.studyhubserver.apply.dto.response.FindParticipateApplyResponse;
import kr.co.studyhubinu.studyhubserver.apply.dto.response.FindRejectApplyResponse;
import kr.co.studyhubinu.studyhubserver.apply.repository.ApplyRepository;
import kr.co.studyhubinu.studyhubserver.common.dto.Converter;
import kr.co.studyhubinu.studyhubserver.exception.apply.ApplyNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.apply.SameUserRequestException;
import kr.co.studyhubinu.studyhubserver.exception.study.PostNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.study.StudyNotFoundException;
import kr.co.studyhubinu.studyhubserver.exception.study.StudyPostClosedException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotAccessRightException;
import kr.co.studyhubinu.studyhubserver.exception.user.UserNotFoundException;
import kr.co.studyhubinu.studyhubserver.reject.repository.RejectRepository;
import kr.co.studyhubinu.studyhubserver.study.domain.StudyEntity;
import kr.co.studyhubinu.studyhubserver.study.repository.StudyRepository;
import kr.co.studyhubinu.studyhubserver.studypost.domain.StudyPostEntity;
import kr.co.studyhubinu.studyhubserver.studypost.domain.implementations.StudyPostApplyEventPublisher;
import kr.co.studyhubinu.studyhubserver.studypost.repository.StudyPostRepository;
import kr.co.studyhubinu.studyhubserver.user.domain.UserEntity;
import kr.co.studyhubinu.studyhubserver.user.dto.data.UserId;
import kr.co.studyhubinu.studyhubserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kr.co.studyhubinu.studyhubserver.apply.enums.Inspection.ACCEPT;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ApplyService {

    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final ApplyRepository applyRepository;
    private final RejectRepository rejectRepository;
    private final StudyPostRepository studyPostRepository;
    private final StudyPostApplyEventPublisher studyPostApplyEventPublisher;
    private final ApplyWriter applyWriter;

    @Transactional
    public void enroll(UserId userId, EnrollApplyRequest request) {
        UserEntity user = userRepository.findById(userId.getId()).orElseThrow(UserNotFoundException::new);
        StudyEntity study = studyRepository.findById(request.getStudyId()).orElseThrow();
        validateSameRequest(user, study);
        StudyPostEntity studyPost = studyPostRepository.findByStudyId(study.getId()).orElseThrow(PostNotFoundException::new);
        validateEnrollClosedPost(studyPost);
        applyRepository.save(ApplyEntity.of(user.getId(), study.getId(), request.getIntroduce()));
    }

    public FindApplyResponse findApply(FindApplyRequest request, final int page, final int size) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<ApplyUserData> userData = Converter.toSlice(pageable, applyRepository.findStudyByIdAndInspection(request, pageable));
        return new FindApplyResponse((long) size, userData);
    }

    public FindApplyResponse findApplyV2(Long userId, FindApplyRequest request, int page, int size) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        StudyEntity study = studyRepository.findById(request.getStudyId()).orElseThrow(StudyNotFoundException::new);
        validIsMasterUserOfStudy(user, study);
        Pageable pageable = PageRequest.of(page, size);
        Slice<ApplyUserData> userData = Converter.toSlice(pageable, applyRepository.findStudyByIdAndInspection(request, pageable));
        return new FindApplyResponse((long) size, userData);
    }

    public FindRejectApplyResponse findRejectApply(Long userId, Long studyId, int page, int size) {
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        StudyEntity study = studyRepository.findById(studyId).orElseThrow(StudyNotFoundException::new);
        validIsMasterUserOfStudy(user, study);
        Pageable pageable = PageRequest.of(page, size);
        Slice<RejectApplyUserData> rejectApplyUserData = Converter.toSlice(pageable, applyRepository.findRejectStudyById(studyId, pageable));
        return new FindRejectApplyResponse((long) size, rejectApplyUserData);
    }

    private void validIsMasterUserOfStudy(UserEntity user, StudyEntity study) {
        if (!study.isMasterOfUser(user.getId())) throw new UserNotAccessRightException();
    }

    public FindParticipateApplyResponse getParticipateApply(final UserId userId, final int page, final int size) {
        UserEntity user = userRepository.findById(userId.getId()).orElseThrow(UserNotFoundException::new);
        final Pageable pageable = PageRequest.of(page, size);
        Long totalCount = applyRepository.countByUserIdAndInspection(user.getId(), ACCEPT);
        Slice<ParticipateApplyData> participateApplyData = Converter.toSlice
                (pageable, applyRepository.findByUserIdAndInspection(user.getId(), pageable));
        return new FindParticipateApplyResponse(totalCount, participateApplyData);
    }

    @Transactional
    public void rejectApply(final RejectApplyRequest rejectApplyRequest, final UserId userId) {
        userRepository.findById(userId.getId()).orElseThrow(UserNotFoundException::new);
        StudyEntity study = studyRepository.findById(rejectApplyRequest.getStudyId()).orElseThrow(StudyNotFoundException::new);
        ApplyEntity applyEntity = applyRepository.findByUserIdAndStudyId(rejectApplyRequest.getRejectedUserId(), study.getId()).orElseThrow(ApplyNotFoundException::new);
        applyEntity.updateReject();
        rejectRepository.save(rejectApplyRequest.toRejectEntity());
    }

    @Transactional
    public void acceptApply(AcceptApplyRequest acceptApplyRequest, UserId userId) {
        userRepository.findById(userId.getId()).orElseThrow(UserNotFoundException::new);
        StudyEntity study = studyRepository.findById(acceptApplyRequest.getStudyId()).orElseThrow(StudyNotFoundException::new);
        ApplyEntity applyEntity = applyRepository.findByUserIdAndStudyId(acceptApplyRequest.getRejectedUserId(), study.getId()).orElseThrow(ApplyNotFoundException::new);
        applyWriter.applyAccept(applyEntity);
        studyPostApplyEventPublisher.acceptApplyEventPublish(study.getId());
    }

    @Transactional
    public void deleteMyStudy(UserId userId, Long studyId) {
        applyRepository.deleteByUserIdAndStudyId(userId.getId(), studyId);
    }

    public FindMyRequestApplyResponse getMyRequestApply(UserId userId, int page, int size) {
        userRepository.findById(userId.getId()).orElseThrow(UserNotFoundException::new);
        final Pageable pageable = PageRequest.of(page, size);
        Slice<RequestApplyData> requestApplyData = Converter.toSlice(pageable, applyRepository.findApplyByUserId(userId.getId(), pageable));

        return new FindMyRequestApplyResponse((long) size, requestApplyData);
    }

    private void validateSameRequest(UserEntity user, StudyEntity study) {
        if (applyRepository.findByUserIdAndStudyId(user.getId(), study.getId()).isPresent()) {
            throw new SameUserRequestException();
        }
    }

    private void validateEnrollClosedPost(StudyPostEntity studyPost) {
        if (studyPost.isClose()) {
            throw new StudyPostClosedException();
        }
    }
}
