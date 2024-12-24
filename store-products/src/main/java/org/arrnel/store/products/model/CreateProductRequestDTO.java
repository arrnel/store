package org.arrnel.store.products.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.arrnel.store.products.validation.ValidPrice;

public record CreateProductRequestDTO(

        @NotNull(message = "{products.create.errors.title.not_null}")
        @Size(min = 3, max = 50, message = "{products.create.errors.title.invalid_length}")
        @JsonProperty("title")
        String title,

        @Size(max = 1000, message = "{products.create.errors.description.invalid_length}")
        @JsonProperty("description")
        String description,

        @NotNull(message="{products.create.errors.price.not_null}")
        @ValidPrice(minPrice = 0.01, message = "{products.create.errors.price.invalid_value}")
        @JsonProperty("price")
        Double price

) {
}
