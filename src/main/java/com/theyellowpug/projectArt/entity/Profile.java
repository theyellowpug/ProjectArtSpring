package com.theyellowpug.projectArt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Profile {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String title;

    private String shortDescription;

    private String longDescription;

    @JsonBackReference
    @ToString.Exclude
    @OneToOne()
    private Client client;

}
