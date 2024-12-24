package org.arrnel.store.products.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.arrnel.store.products.validation.validator.PricesRangeValidator;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = PricesRangeValidator.class)
@Documented
public @interface ValidPricesRange {

    String message() default "Min price can't be greater than max price";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}