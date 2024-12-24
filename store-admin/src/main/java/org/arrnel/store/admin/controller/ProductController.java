package org.arrnel.store.admin.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.arrnel.store.admin.entity.Product;
import org.arrnel.store.admin.ex.BadRequestException;
import org.arrnel.store.admin.ex.ProductNotFoundException;
import org.arrnel.store.admin.mapper.ProductMapper;
import org.arrnel.store.admin.model.UpdateProductRequestDTO;
import org.arrnel.store.admin.service.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@Controller
@RequiredArgsConstructor
@RequestMapping(("/products/{product_id:\\d+}"))
@ParametersAreNonnullByDefault
public class ProductController {

    private final ProductsService productsService;

    @Nonnull
    @ModelAttribute("product")
    public Product product(@Valid @Min(1) @PathVariable("product_id") Long productId) {
        return this.productsService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Nonnull
    @GetMapping
    public String getProduct() {
        return "products/product";
    }

    @Nonnull
    @GetMapping("/edit")
    public String getProductEditPage() {
        return "products/edit";
    }

    @Nonnull
    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute(name = "product", binding = false) Product oldProduct,
                                @Valid UpdateProductRequestDTO requestDTO,
                                Model model) {
        try {
            this.productsService.update(oldProduct, ProductMapper.fromUpdateDTO(requestDTO));
            return "redirect:/products/%d".formatted(oldProduct.getId());
        } catch (BadRequestException exception) {
            model.addAttribute("payload", requestDTO);
            model.addAttribute("errors", exception.getErrors());
            return "products/edit";
        }
    }

    @Nonnull
    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        this.productsService.delete(product);
        return "redirect:/products";
    }

}
