package kr.co.studyhubinu.studyhubserver.notice.service;


import kr.co.studyhubinu.studyhubserver.common.dto.Converter;
import kr.co.studyhubinu.studyhubserver.notice.dto.response.FindNoticeResponse;
import kr.co.studyhubinu.studyhubserver.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Slice<FindNoticeResponse> getNotice(int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return Converter.toSlice(pageable, noticeRepository.findNoticeAll(pageable));
    }
}
