package kr.co.studyhubinu.studyhubserver.apply.domain.implementations;

import kr.co.studyhubinu.studyhubserver.apply.domain.ApplyEntity;
import kr.co.studyhubinu.studyhubserver.apply.repository.ApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ApplyWriter {

    private final ApplyRepository applyRepository;

    @Transactional
    public void applyAccept(ApplyEntity applyEntity) {
        applyEntity.updateAccept();
        applyRepository.save(applyEntity);
    }
}
