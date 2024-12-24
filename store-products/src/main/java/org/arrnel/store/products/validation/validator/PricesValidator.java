package org.arrnel.store.products.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.arrnel.store.products.validation.ValidPrice;

public class PricesValidator implements ConstraintValidator<ValidPrice, Double> {

    private ValidPrice anno;

    @Override
    public void initialize(ValidPrice constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        anno = constraintAnnotation;
    }

    @Override
    public boolean isValid(Double price, ConstraintValidatorContext constraintValidatorContext) {
        return price != null && price >= anno.minPrice() && price <= anno.maxPrice();
    }

}
