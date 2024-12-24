package org.arrnel.store.products.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
public record PageResponseDTO (

        @JsonProperty("current_page")
        Integer currentPage,

        @JsonProperty("pages")
        Integer totalPages,

        @JsonProperty("items_per_page")
        Integer itemsPerPage,

        @JsonProperty("total_items")
        Long totalItems,

        @JsonProperty("data")
        List<GetProductResponseDTO> data

) implements Serializable {


    @Builder
    @JsonCreator
    public PageResponseDTO {

        if (currentPage == null)
            currentPage = 0;

        if (totalPages == null)
            totalPages = 0;

        if (itemsPerPage == null)
            itemsPerPage = 0;

        if (totalItems == null)
            totalItems = 0L;

        if (data == null)
            data = new ArrayList<>();

    }

}