package my.spring.app.test.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RatingConstraintValidator implements ConstraintValidator<Rating1To10, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value >= 1 && value <= 10) return true;
        return false;
    }

    @Override
    public void initialize(Rating1To10 constraintAnnotation) {
    }
}
