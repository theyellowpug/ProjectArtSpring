package com.theyellowpug.projectArt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.theyellowpug.projectArt.model.ProductStatus;
import com.theyellowpug.projectArt.model.ProductType;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private ProductType productType;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    private String description;

    private ProductStatus productStatus;

    private Long quantity;

    /**
     * If product has two pictures: "name0.jpg;name1.jpg"
     */
    private String images;

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne()
    private Client owner;

    @JsonBackReference
    @ToString.Exclude
    @ManyToMany()
    private Set<Client> customers;

    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany()
    private List<ProductTag> productTags;

}
