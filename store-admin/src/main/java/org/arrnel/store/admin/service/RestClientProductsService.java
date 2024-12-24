package org.arrnel.store.admin.service;

import lombok.RequiredArgsConstructor;
import org.arrnel.store.admin.entity.Product;
import org.arrnel.store.admin.ex.BadRequestException;
import org.arrnel.store.admin.ex.ProductNotFoundException;
import org.arrnel.store.admin.mapper.ProductMapper;
import org.arrnel.store.admin.model.PageResponseDTO;
import org.arrnel.store.admin.model.Pageable;
import org.arrnel.store.admin.model.ProductsFilter;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@ParametersAreNonnullByDefault
public class RestClientProductsService implements ProductsService {

    private final RestClient restClient;

    @Override
    @SuppressWarnings("unchecked")
    public Product create(Product product) {
        try {
            return restClient
                    .post()
                    .uri("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ProductMapper.toCreateDTO(product))
                    .retrieve()
                    .body(Product.class);
        } catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Nonnull
    @Override
    public Optional<Product> findById(Long productId) {
        try {
            return Optional.ofNullable(restClient.get()
                    .uri("/api/products/{productId}", productId)
                    .retrieve()
                    .body(Product.class));
        } catch (HttpClientErrorException.NotFound exception) {
            return Optional.empty();
        }
    }

    @Nonnull
    @Override
    public PageResponseDTO findAll(
            ProductsFilter productsFilter,
            Pageable pageable
    ) {
        var uri = UriComponentsBuilder
                .fromPath("/api/products")
                .queryParamIfPresent("title", Optional.ofNullable(productsFilter.title()))
                .queryParamIfPresent("min_price", Optional.ofNullable(productsFilter.minPrice()))
                .queryParamIfPresent("max_price", Optional.ofNullable(productsFilter.maxPrice()))
                .queryParamIfPresent(
                        "page",
                        (pageable.page() == null || pageable.page() <= 0)
                                ? Optional.empty()
                                : Optional.of(pageable.page()))
                .queryParamIfPresent("sort", pageable.sort().getSortText())
                .toUriString();

        return Objects.requireNonNull(
                restClient.get()
                        .uri(uri)
                        .retrieve()
                        .body(PageResponseDTO.class));
    }

    @Nonnull
    @Override
    public Product update(Product oldProduct, Product newProduct) {
        Product product = ProductMapper.update(oldProduct, newProduct);
        try {
            this.restClient
                    .patch()
                    .uri("/api/products/{product_id}", oldProduct.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(product)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
        return product;
    }

    @Override
    public void delete(Product product) {
        try {
            this.restClient
                    .delete()
                    .uri("/api/products/{productId}", product.getId())
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound exception) {
            throw new ProductNotFoundException(product.getId());
        }
    }
}
