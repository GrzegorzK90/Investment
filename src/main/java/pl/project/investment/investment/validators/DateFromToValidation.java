package pl.project.investment.investment.validators;

import pl.project.investment.investment.JSON.InvestmentModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateFromToValidation
        implements ConstraintValidator<DataFromToValidationAnnotation, InvestmentModel> {

    public void initialize(DataFromToValidationAnnotation constraint) {
    }

    public boolean isValid(InvestmentModel obj, ConstraintValidatorContext context) {
        return (obj.getDateFrom().isBefore(obj.getDateTo()));
    }
}
