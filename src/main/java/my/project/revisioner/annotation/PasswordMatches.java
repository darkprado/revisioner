package my.project.revisioner.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import my.project.revisioner.validation.PasswordMatchesValidator;

import java.lang.annotation.*;

/**
 * @author s.melekhin
 * @since 24 май 2023 г.
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {

    String message() default "Password not matches";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
