package kr.co.studyhubinu.studyhubserver.bookmark.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookMarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "user_id")
    private Long userId;
}
