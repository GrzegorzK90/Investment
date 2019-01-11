package pl.project.investment.investment.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = DepositPeriodValidation.class)
@Target({FIELD})
@Retention(RUNTIME)
@Documented
public @interface DepositPeriodValidationAnnotation {

    String message() default "wrong deposit period. Possible value 1,3,6,12 months";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
