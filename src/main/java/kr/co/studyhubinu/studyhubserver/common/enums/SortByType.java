package kr.co.studyhubinu.studyhubserver.common.enums;

public enum SortByType implements EnumModel {
    ByTime("createdDate"),
    ByPopularity("popularity"),
    ByName("name");

    private String value;

    SortByType(String value) {
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
