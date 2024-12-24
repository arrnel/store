package org.arrnel.store.admin.mapper;

import org.arrnel.store.admin.entity.Product;
import org.arrnel.store.admin.model.*;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@ParametersAreNonnullByDefault
public class ProductMapper {

    private ProductMapper() {
    }

    @Nonnull
    public static Product fromCreateDTO(CreateProductRequestDTO dto) {
        return Product.builder()
                .title(dto.title())
                .description(dto.description())
                .price(dto.price())
                .build();
    }

    @Nonnull
    public static CreateProductResponseDTO toCreateDTO(Product entity) {
        return CreateProductResponseDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .build();
    }

    @Nonnull
    public static GetProductResponseDTO toGetDTO(Product entity) {
        return GetProductResponseDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .build();
    }

    @Nonnull
    public static Product fromUpdateDTO(UpdateProductRequestDTO dto) {
        return Product.builder()
                .title(dto.title())
                .description(dto.description())
                .price(dto.price())
                .build();
    }

    @Nonnull
    public static UpdateProductResponseDTO toUpdateDTO(Product entity) {
        return UpdateProductResponseDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .build();
    }

    @Nonnull
    public static Product update(Product oldProduct, Product newProduct) {
        return Product.builder()
                .id(oldProduct.getId())
                .title(
                        isEmpty(newProduct.getTitle()) || isBlank(newProduct.getTitle())
                                ? oldProduct.getTitle()
                                : newProduct.getTitle())
                .description(
                        isEmpty(newProduct.getDescription()) || isBlank(newProduct.getDescription())
                                ? oldProduct.getDescription()
                                : newProduct.getDescription())
                .price(
                        newProduct.getPrice() == null || newProduct.getPrice() <= 0
                                ? oldProduct.getPrice()
                                : newProduct.getPrice())
                .build();

    }

}