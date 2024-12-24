package org.arrnel.store.admin.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.arrnel.store.admin.model.ProductsFilter;
import org.arrnel.store.admin.validation.ValidPricesRange;

public class PricesRangeValidator implements ConstraintValidator<ValidPricesRange, ProductsFilter> {

    @Override
    public boolean isValid(ProductsFilter productsFilter, ConstraintValidatorContext constraintValidatorContext) {
        var minPrice = productsFilter.minPrice();
        var maxPrice = productsFilter.maxPrice();
        if (minPrice != null && maxPrice != null)
            return minPrice <= maxPrice;
        return true;
    }

}
