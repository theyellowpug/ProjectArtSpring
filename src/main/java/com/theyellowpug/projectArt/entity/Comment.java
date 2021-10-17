package com.theyellowpug.projectArt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne()
    private Product product;

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne()
    private Client owner;

}
