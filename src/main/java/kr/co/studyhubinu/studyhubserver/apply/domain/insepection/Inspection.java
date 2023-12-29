package kr.co.studyhubinu.studyhubserver.apply.domain.insepection;

import kr.co.studyhubinu.studyhubserver.exception.apply.InspectionNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Inspection {

    ACCEPT("Accept"),
    STANDBY("StandBy"),
    REJECT("Reject");

    private String value;

    Inspection(String value) {
        this.value = value;
    }

    public static Inspection fromCode(String dbData) {
        return Arrays.stream(Inspection.values())
                .filter(v -> v.getValue().equals(dbData))
                .findAny()
                .orElseThrow(InspectionNotFoundException::new);
    }
}
