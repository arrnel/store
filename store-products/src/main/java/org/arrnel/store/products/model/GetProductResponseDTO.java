package org.arrnel.store.products.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record GetProductResponseDTO(

        @JsonProperty("id")
        Long id,

        @JsonProperty("title")
        String title,

        @JsonProperty("description")
        String description,

        @JsonProperty("price")
        Double price

) {
}
