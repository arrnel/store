package org.arrnel.store.admin.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.arrnel.store.admin.validation.validator.PriceValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.FIELD,
        ElementType.PARAMETER
})
@Constraint(validatedBy = PriceValidator.class)
public @interface ValidPrice {

    double min() default 0.01d;

    double max() default Double.MAX_VALUE;

    String message() default "invalid decimal range";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
