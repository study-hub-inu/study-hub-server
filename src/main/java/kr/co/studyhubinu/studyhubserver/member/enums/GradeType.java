package kr.co.studyhubinu.studyhubserver.member.enums;

import kr.co.studyhubinu.studyhubserver.common.enums.EnumModel;

public enum GradeType implements EnumModel {

    FIRST("1학년"),

    SECOND("2학년"),

    THIRD("3학년"),

    FOURTH("4학년");

    private String value;

    GradeType(String value) {
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
