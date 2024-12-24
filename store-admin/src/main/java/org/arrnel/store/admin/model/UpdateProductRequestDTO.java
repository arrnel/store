package org.arrnel.store.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import org.arrnel.store.admin.validation.ValidPrice;

public record UpdateProductRequestDTO(

        @Size(min = 3, max = 50, message = "{products.update.errors.title.invalid_length}")
        @JsonProperty("title")
        String title,

        @Size(max = 1000, message = "{products.update.errors.description.invalid_length}")
        @JsonProperty("description")
        String description,

        @ValidPrice(message = "{products.create.errors.price.range}")
        @JsonProperty("price")
        Double price

) {
}
