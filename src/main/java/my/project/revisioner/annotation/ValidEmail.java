package my.project.revisioner.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import my.project.revisioner.validation.EmailValidator;

import java.lang.annotation.*;

/**
 * @author s.melekhin
 * @since 24 май 2023 г.
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {

    String message() default "Invalid email";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};

}
