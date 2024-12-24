package org.arrnel.store.products.specs.value;

import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@Component
@ParametersAreNonnullByDefault
public class PriceRangeSpecification {

    public void specify(
            String fieldName,
            @Nullable Double min,
            @Nullable Double max,
            Root<?> root,
            CriteriaBuilder builder,
            List<Predicate> predicates
    ) {

        if (min != null && max != null) {
            predicates.add(
                    builder.between(
                            root.get(fieldName),
                            min,
                            max));
        } else if (min != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(
                            root.get(fieldName),
                            min));
        } else if (max != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(
                            root.get(fieldName),
                            max));
        }

    }

}
