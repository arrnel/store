package org.arrnel.store.admin.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.arrnel.store.admin.validation.ValidPrice;


public class PriceValidator implements ConstraintValidator<ValidPrice, Double> {

    private ValidPrice anno;

    @Override
    public void initialize(ValidPrice constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        anno = constraintAnnotation;
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value != null && (value < anno.min() || value > anno.max())) {
            context
                    .buildConstraintViolationWithTemplate("price should be between [%s; %s]".formatted(anno.min(), anno.max()))
                    .addPropertyNode("price");
            return false;
        }
        return true;
    }

}
