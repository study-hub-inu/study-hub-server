package kr.co.studyhubinu.studyhubserver.user.enums;

import kr.co.studyhubinu.studyhubserver.common.enums.EnumModel;

public enum GenderType implements EnumModel {
    MALE("남자"),
    FEMALE("여자"),
    NULL("null");

    private String value;

    GenderType(String value) {
        this.value = value;
    }

    /**
     * enum key 리턴
     * @return 'Male' or 'FEMALE'
     */
    @Override
    public String getKey() {
        return name();
    }

    /**
     * enum value 리턴
     * @return '남자' or '여자'
     */
    @Override
    public String getValue() {
        return value;
    }
}