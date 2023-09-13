package kr.co.studyhubinu.studyhubserver.study.enums;

import kr.co.studyhubinu.studyhubserver.common.enums.EnumModel;

public enum StudyWayType implements EnumModel {
    CONTACT("대면"),
    UNTACT("비대면"),
    MIX("혼합");

    private String value;

    StudyWayType(String value) {
        this.value = value;
    }

    /**
     * enum key 리턴
     * @return 'CONTACT' or 'UNTACT'
     */
    @Override
    public String getKey() {
        return name();
    }

    /**
     * enum value 리턴
     * @return '대면' or '비대면'
     */
    @Override
    public String getValue() {
        return value;
    }
}
