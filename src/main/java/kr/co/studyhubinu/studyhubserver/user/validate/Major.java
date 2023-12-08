package kr.co.studyhubinu.studyhubserver.user.validate;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MajorValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Major {

    String message() default "존재하지 않는 전공입니다.";

}
