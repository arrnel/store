package org.arrnel.store.admin.service;

import lombok.NonNull;
import org.arrnel.store.admin.entity.Product;
import org.arrnel.store.admin.model.PageResponseDTO;
import org.arrnel.store.admin.model.Pageable;
import org.arrnel.store.admin.model.ProductsFilter;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@ParametersAreNonnullByDefault
public interface ProductsService {

    Product create(Product product);

    Optional<Product> findById(Long productId);

    @NonNull
    PageResponseDTO findAll(ProductsFilter productsFilter, Pageable pageable);

    Product update(Product oldProduct, Product newProduct);

    void delete(Product product);
}
