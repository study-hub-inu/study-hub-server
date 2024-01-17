package kr.co.studyhubinu.studyhubserver.notification.enums;

import kr.co.studyhubinu.studyhubserver.common.enums.EnumModel;

public enum NotificationType implements EnumModel {
    APPLY("신청"),
    PARTICIPATION("참여"),
    REFUSE("거절"),
    RECRUIT("모집 완료"),
    BOOKMARK("북마크");

    private String value;

    NotificationType(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
