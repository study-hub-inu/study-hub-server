package kr.co.studyhubinu.studyhubserver.alarm.enums;

import kr.co.studyhubinu.studyhubserver.common.enums.EnumModel;

public enum AlarmType implements EnumModel {
    APPLY("신청"),
    PARTICIPATION("참여"),
    REFUSE("거절"),
    RECRUIT("모집 완료"),
    BOOKMARK("북마크");

    private String value;

    AlarmType(String value) {
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
