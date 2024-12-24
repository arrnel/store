package org.arrnel.store.products.service;

import lombok.RequiredArgsConstructor;
import org.arrnel.store.products.entity.Product;
import org.arrnel.store.products.mapper.ProductMapper;
import org.arrnel.store.products.model.ProductsFilter;
import org.arrnel.store.products.repository.ProductRepository;
import org.arrnel.store.products.specs.ProductSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@ParametersAreNonnullByDefault
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;
    private final ProductSpecs productSpecs;

    @Nonnull
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Nonnull
    @Override
    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    @Nonnull
    @Override
    public Page<Product> findAll(ProductsFilter productsFilter, Pageable pageable) {
        return productRepository.findAll(productSpecs.findByCriteria(productsFilter), pageable);
    }

    @Nonnull
    @Override
    public Product update(Product oldProduct, Product newProduct) {
        return productRepository.save(
                ProductMapper.update(
                        oldProduct,
                        newProduct));
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }
}
