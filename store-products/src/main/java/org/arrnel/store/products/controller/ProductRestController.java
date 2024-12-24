package org.arrnel.store.products.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.arrnel.store.products.entity.Product;
import org.arrnel.store.products.ex.ProductNotFoundException;
import org.arrnel.store.products.mapper.ProductMapper;
import org.arrnel.store.products.model.GetProductResponseDTO;
import org.arrnel.store.products.model.UpdateProductRequestDTO;
import org.arrnel.store.products.model.UpdateProductResponseDTO;
import org.arrnel.store.products.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.ParametersAreNonnullByDefault;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/{product_id:\\d+}")
@ParametersAreNonnullByDefault
public class ProductRestController {

    private final ProductService productService;

    @ModelAttribute("product")
    public Product getProduct(@Valid @Min(1) @PathVariable("product_id") Long productId) {
        return productService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @GetMapping
    public ResponseEntity<GetProductResponseDTO> findById(@ModelAttribute("product") Product product) {
        return ResponseEntity.ok(ProductMapper
                .toGetDTO(product));
    }

    @PatchMapping
    public ResponseEntity<UpdateProductResponseDTO> update(@ModelAttribute("product") Product oldProduct,
                                                           @Valid @RequestBody UpdateProductRequestDTO requestDTO,
                                                           BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors())
            throw bindingResult instanceof BindException exception
                    ? exception
                    : new BindException(bindingResult);

        Product product = productService.update(oldProduct, ProductMapper.fromUpdateDTO(requestDTO));
        return ResponseEntity.status(HttpStatus.OK)
                .body(ProductMapper.toUpdateDTO(product));

    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@ModelAttribute("product") Product product) {
        productService.delete(product);
        return ResponseEntity.noContent()
                .build();
    }

}
