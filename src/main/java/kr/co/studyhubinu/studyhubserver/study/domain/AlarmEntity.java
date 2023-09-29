package kr.co.studyhubinu.studyhubserver.study.domain;

import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import kr.co.studyhubinu.studyhubserver.study.enums.AlarmCategoryType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "alarm")
public class AlarmEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "alarm_category")
    private AlarmCategoryType alarmCategory;

    @Column(name = "post_id")
    private Long postId;

    private String title;
}
