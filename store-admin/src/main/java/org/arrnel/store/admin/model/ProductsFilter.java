package org.arrnel.store.admin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import org.arrnel.store.admin.validation.ValidPrice;
import org.arrnel.store.admin.validation.ValidPricesRange;

@ValidPricesRange(message = "{products.get.errors.price.invalid_range}")
public record ProductsFilter(

        @Size(min = 3, max = 50, message="{products.get.errors.title.invalid_length}")
        @JsonProperty("title")
        String title,

        @ValidPrice(min = 0, message="{products.get.errors.price.invalid_value}")
        @JsonProperty("min_price")
        Double minPrice,

        @ValidPrice(min = 0, message="{products.get.errors.price.invalid_value}")
        @JsonProperty("max_price")
        Double maxPrice

) {
}
