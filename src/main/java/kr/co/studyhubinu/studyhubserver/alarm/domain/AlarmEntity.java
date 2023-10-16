package kr.co.studyhubinu.studyhubserver.alarm.domain;

import kr.co.studyhubinu.studyhubserver.alarm.enums.AlarmType;
import kr.co.studyhubinu.studyhubserver.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "alarm")
public class AlarmEntity extends BaseTimeEntity {

    @Id
    @Column(name = "alarm_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "alarm_category")
    private AlarmType alarmCategory;

    private boolean checked;

    public void read() {
        checked = true;
    }

    public boolean isAlarmOfUser(Long userId) {
        return this.userId.equals(userId);
    }
}
