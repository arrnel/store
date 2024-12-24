package org.arrnel.store.products.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = "store", name = "products")
public class Product {

    @With
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ToString.Include
    @Column(name = "title", nullable = false)
    private String title;

    @ToString.Include
    @Column(name = "description")
    private String description;

    @ToString.Include
    @Column(name = "price")
    private Double price;

}
