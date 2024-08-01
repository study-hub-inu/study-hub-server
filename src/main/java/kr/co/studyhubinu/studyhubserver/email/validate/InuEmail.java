package kr.co.studyhubinu.studyhubserver.email.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = InuEmailValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface InuEmail {
    String message() default "이메일 형식에 맞지 않습니다. (인천대학교 이메일 주소만 가능)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}