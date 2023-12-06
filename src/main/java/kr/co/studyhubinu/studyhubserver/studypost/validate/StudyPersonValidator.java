package kr.co.studyhubinu.studyhubserver.studypost.validate;

import kr.co.studyhubinu.studyhubserver.email.validate.InuEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudyPersonValidator implements ConstraintValidator<MinStudyPerson, Integer> {

    @Override
    public void initialize(MinStudyPerson constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value >= 2;
    }
}
