package org.arrnel.store.products.specs.value;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EqualTextSpecification {

    public void specify(
            @Nonnull String fieldName,
            @Nullable String text,
            @Nonnull Root<?> root,
            @Nonnull CriteriaBuilder builder,
            @Nonnull List<Predicate> predicates
    ) {
        if (text != null && !text.trim().isEmpty())
            predicates.add(builder.equal(root.get(fieldName), text));
    }

}
