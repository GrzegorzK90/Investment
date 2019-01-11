package pl.project.investment.investment.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepositPeriodValidation implements ConstraintValidator<DepositPeriodValidationAnnotation, Integer> {
    public void initialize(DepositPeriodValidationAnnotation constraint) {
    }

    public boolean isValid(Integer obj, ConstraintValidatorContext context) {
        return (obj.equals(1) || obj.equals(3) || obj.equals(6) || obj.equals(12));
    }
}
