package org.arrnel.store.products.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import org.arrnel.store.products.validation.ValidPrice;

public record UpdateProductRequestDTO(

        @JsonProperty("title")
        @Size(min = 3, max = 50, message = "{products.update.errors.title.invalid_length}")
        String title,

        @JsonProperty("description")
        @Size(max = 1000, message = "{products.update.errors.description.invalid_length}")
        String description,

        @JsonProperty("price")
        @ValidPrice(minPrice = 0.01, message = "{products.create.errors.price.invalid_value}")
        Double price

) {
}
