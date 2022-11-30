package my.spring.app.test.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MovieYearValidator implements ConstraintValidator<MovieYear, Integer> {

    private int oldestPossible;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value > oldestPossible) return true;
        return false;
    }

    @Override
    public void initialize(MovieYear constraintAnnotation) {
        this.oldestPossible = constraintAnnotation.oldestPossible();
    }
}
