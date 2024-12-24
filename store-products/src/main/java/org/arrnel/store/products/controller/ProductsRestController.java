package org.arrnel.store.products.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.arrnel.store.products.entity.Product;
import org.arrnel.store.products.mapper.ProductMapper;
import org.arrnel.store.products.model.CreateProductRequestDTO;
import org.arrnel.store.products.model.CreateProductResponseDTO;
import org.arrnel.store.products.model.PageResponseDTO;
import org.arrnel.store.products.model.ProductsFilter;
import org.arrnel.store.products.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@ParametersAreNonnullByDefault
public class ProductsRestController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<CreateProductResponseDTO> createProduct(@Valid @RequestBody CreateProductRequestDTO requestDTO,
                                                                  BindingResult bindingResult,
                                                                  UriComponentsBuilder uriComponentsBuilder
    ) throws BindException {

        if (bindingResult.hasErrors())
            throw bindingResult instanceof BindException exception
                    ? exception
                    : new BindException(bindingResult);

        Product product = this.productService.create(ProductMapper.fromCreateDTO(requestDTO));
        return ResponseEntity.created(uriComponentsBuilder
                                .replacePath("/api/products/{product_id}")
                                .build(Map.of("product_id", product.getId())))
                .body(ProductMapper
                        .toCreateDTO(product));
    }

    @GetMapping
    public PageResponseDTO findAll(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "min_price", required = false) Double minPrice,
            @RequestParam(value = "max_price", required = false) Double maxPrice,
            @PageableDefault Pageable pageable
    ) {
        ProductsFilter productsFilter = new ProductsFilter(title, minPrice, maxPrice);
        return ProductMapper.toPage(productService.findAll(productsFilter, pageable));
    }

}
