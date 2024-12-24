package org.arrnel.store.admin.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.arrnel.store.admin.validation.validator.PricesRangeValidator;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = PricesRangeValidator.class)
@Documented
public @interface ValidPricesRange {

    String message() default "{products.get.errors.price.invalid_range}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}