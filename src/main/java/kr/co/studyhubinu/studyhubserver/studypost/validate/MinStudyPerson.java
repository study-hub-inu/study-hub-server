package kr.co.studyhubinu.studyhubserver.studypost.validate;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StudyPersonValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinStudyPerson {

    String message() default "스터디 정원은 2명 이상입니다!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
