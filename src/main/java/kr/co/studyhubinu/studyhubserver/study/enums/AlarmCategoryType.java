package kr.co.studyhubinu.studyhubserver.study.enums;

import kr.co.studyhubinu.studyhubserver.common.enums.EnumModel;

public enum AlarmCategoryType implements EnumModel {

    ACTIVITY("활동"),
    INTEREST("관심사"),
    MAJOR("학과");

    private String value;

    AlarmCategoryType(String value) {
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
