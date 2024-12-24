package org.arrnel.store.products.specs;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.arrnel.store.products.entity.Product;
import org.arrnel.store.products.model.ProductsFilter;
import org.arrnel.store.products.specs.value.PartialTextSpecification;
import org.arrnel.store.products.specs.value.PriceRangeSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSpecs implements EntitySpecification<Specification<Product>, ProductsFilter>  {

    private static final String C_TITLE = "title";
    private static final String C_PRICE = "price";
    private final PartialTextSpecification partialTextSpec;
    private final PriceRangeSpecification priceRangeSpec;

    @Override
    public Specification<Product> findByCriteria(ProductsFilter source) {

        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            partialTextSpec.specify(C_TITLE, source.title(), root, builder, predicates);
            priceRangeSpec.specify(C_PRICE, source.minPrice(), source.maxPrice(), root, builder, predicates);

            return builder.and(predicates.toArray(new Predicate[0]));

        };

    }

}
