package pl.project.investment.investment.validators;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@SuppressWarnings("unused")
@Constraint(validatedBy = {DateFromToValidation.class})
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
public @interface DataFromToValidationAnnotation {

    String message() default "DateFrom is bigger Than DateTo";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
