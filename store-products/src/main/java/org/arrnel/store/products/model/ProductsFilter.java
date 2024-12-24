package org.arrnel.store.products.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.arrnel.store.products.validation.ValidPricesRange;

@ValidPricesRange
public record ProductsFilter (

        @JsonProperty("title")
        @Size(min = 3, max = 50, message = "{}")
        String title,

        @JsonProperty("min_price")
        @Positive(message = "{products.get.errors.price.invalid_value}")
        Double minPrice,

        @JsonProperty("max_price")
        @Positive(message = "{}")
        Double maxPrice

){
}
