package org.arrnel.store.products.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.arrnel.store.products.validation.validator.PricesValidator;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.PARAMETER})
@Constraint(validatedBy = PricesValidator.class)
@Documented
public @interface ValidPrice {

    double minPrice() default 0.0;

    double maxPrice() default Double.MAX_VALUE;

    String message() default "Min price can't be greater than max price";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}