package kr.co.studyhubinu.studyhubserver.email.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InuEmailValidator implements ConstraintValidator<InuEmail, String> {
    private static final String INU_EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@inu\\.ac\\.kr$";

    @Override
    public void initialize(InuEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return false;
        }
        return email.matches(INU_EMAIL_PATTERN);
    }
}
