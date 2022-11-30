package my.spring.app.test.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MovieYearValidator.class)
public @interface Rating1To10 {
    String message() default "Invalid rating: must be from 1 to 10";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
