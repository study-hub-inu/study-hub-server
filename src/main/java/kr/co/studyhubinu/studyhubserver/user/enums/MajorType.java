package kr.co.studyhubinu.studyhubserver.user.enums;

import kr.co.studyhubinu.studyhubserver.common.enums.EnumModel;

public enum MajorType implements EnumModel {
    COMPUTER("컴퓨터공학부"),
    INFORMATION("정보통신공학과"),
    DESIGN("디자인학부");

    private String value;

    MajorType(String value) {
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
