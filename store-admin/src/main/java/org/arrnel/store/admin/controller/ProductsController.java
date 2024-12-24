package org.arrnel.store.admin.controller;

import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.arrnel.store.admin.entity.Product;
import org.arrnel.store.admin.ex.BadRequestException;
import org.arrnel.store.admin.mapper.ProductMapper;
import org.arrnel.store.admin.model.CreateProductRequestDTO;
import org.arrnel.store.admin.model.Pageable;
import org.arrnel.store.admin.model.ProductsFilter;
import org.arrnel.store.admin.model.Sort;
import org.arrnel.store.admin.service.ProductsService;
import org.arrnel.store.admin.validation.ValidPrice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
@ParametersAreNonnullByDefault
public class ProductsController {

    private final ProductsService productsService;

    @Nonnull
    @GetMapping("/add")
    public String getNewProductPage() {
        return "products/add";
    }

    @Nonnull
    @PostMapping("/add")
    public String add(@Valid CreateProductRequestDTO requestDTO,
                      Model model) {
        try {
            Product product = productsService.create(ProductMapper.fromCreateDTO(requestDTO));
            return "redirect:/products/%d".formatted(product.getId());
        } catch (BadRequestException exception) {
            model.addAttribute("payload", requestDTO);
            model.addAttribute("errors", exception.getErrors());
            return "add";
        }
    }

    @Nonnull
    @GetMapping
    public String getProductsList(@Valid @Size(min = 3, max = 50, message = "{products.get.errors.title.invalid_length}") @RequestParam(name = "title", required = false) String title,
                                  @Valid @ValidPrice(min = 0.0, message = "{products.get.errors.price.should_be_positive}") @RequestParam(name = "min_price", required = false) Double minPrice,
                                  @Valid @ValidPrice(min = 0.0, message = "{products.get.errors.price.should_be_positive}") @RequestParam(name = "max_price", required = false) Double maxPrice,
                                  @Valid @Min(value = 0, message = "{products.get.errors.page.should_be_positive}") @RequestParam(name = "page", required = false) Integer page,
                                  @RequestParam(name = "field", required = false) String[] fields,
                                  @RequestParam(name = "order", required = false) Sort.Order order,
                                  Model model) {

        ProductsFilter productsFilter = new ProductsFilter(title, minPrice, maxPrice);
        Validator validator = Validation.byDefaultProvider()
                .configure()
                .buildValidatorFactory()
                .getValidator();
        validator.validate(productsFilter);

        var products = productsService.findAll(
                productsFilter,
                new Pageable(page, new Sort(fields, order)));
        model.addAttribute("products", products);
        model.addAttribute("productsFilter", productsFilter);
        return "products/list";
    }
}
