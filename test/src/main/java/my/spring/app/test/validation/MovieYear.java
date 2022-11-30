package my.spring.app.test.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MovieYearValidator.class)
public @interface MovieYear {
    String message() default "There is no movies made before 1880, ok?";
    int oldestPossible() default 1880;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
