package my.project.revisioner.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import my.project.revisioner.annotation.PasswordMatches;
import my.project.revisioner.payload.request.SignupRequest;

/**
 * @author s.melekhin
 * @since 24 май 2023 г.
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        SignupRequest request = (SignupRequest) obj;
        return request.getPassword().equals(request.getConfirmPassword());
    }
}
