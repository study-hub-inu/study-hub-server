package kr.co.studyhubinu.studyhubserver.bookmark.domain;

import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookMarkEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "user_id")
    private Long userId;

    @Builder
    public BookMarkEntity(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }
}
