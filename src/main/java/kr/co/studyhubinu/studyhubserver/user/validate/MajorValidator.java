package kr.co.studyhubinu.studyhubserver.user.validate;

import kr.co.studyhubinu.studyhubserver.user.enums.MajorType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MajorValidator implements ConstraintValidator<Major, String> {

    @Override
    public void initialize(Major constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return MajorType.of(value) != MajorType.NONE;
    }
}
