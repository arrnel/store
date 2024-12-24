package org.arrnel.store.products.service;

import org.arrnel.store.products.entity.Product;
import org.arrnel.store.products.model.ProductsFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@ParametersAreNonnullByDefault
public interface ProductService {

    @Nonnull
    Product create(Product product);

    @Nonnull
    Optional<Product> findById(Long productId);

    @Nonnull
    Page<Product> findAll(ProductsFilter productsFilter, Pageable pageable);

    @Nonnull
    Product update(Product oldProduct, Product newProduct);

    void delete(Product product);

}
