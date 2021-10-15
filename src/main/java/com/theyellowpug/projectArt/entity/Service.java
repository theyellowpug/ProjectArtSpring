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
public class Service {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private Long price;

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne()
    private Client client;
}
