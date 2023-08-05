package kr.co.studyhubinu.studyhubserver.member.dto.data;

import kr.co.studyhubinu.studyhubserver.member.enums.GenderType;
import kr.co.studyhubinu.studyhubserver.member.enums.GradeType;
import lombok.Getter;

@Getter
public class GeneralSignUpInfo {
    private String nickName;
    private String email;
    private String password;
    private GenderType gender;
    private GradeType grade;

    public GeneralSignUpInfo(String nickName, String email, String password, GenderType gender, GradeType grade) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.grade = grade;
    }
}