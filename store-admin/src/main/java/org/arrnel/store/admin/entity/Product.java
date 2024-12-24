package org.arrnel.store.admin.entity;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(onlyExplicitlyIncluded = true)
public class Product {

    @ToString.Include
    private Long id;

    @ToString.Include
    private String title;

    @ToString.Include
    private String description;

    @ToString.Include
    private Double price;

}
