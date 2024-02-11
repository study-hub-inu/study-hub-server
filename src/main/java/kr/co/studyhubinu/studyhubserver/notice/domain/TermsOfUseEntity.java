package kr.co.studyhubinu.studyhubserver.notice.domain;

import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "terms_of_use")
public class TermsOfUseEntity extends BaseTimeEntity {

    @Id
    @Column(name = "terms_of_use_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String version;

    @Builder
    public TermsOfUseEntity(String title, String content, String version) {
        this.title = title;
        this.content = content;
        this.version = version;
    }
}
