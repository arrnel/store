package org.arrnel.store.products.specs;

public interface EntitySpecification<D, S> {
    D findByCriteria(S source);
}
