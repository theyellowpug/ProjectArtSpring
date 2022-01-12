package com.theyellowpug.projectArt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Profile {
    @Id
    @GeneratedValue
    private Long id;

    private String firstname;

    private String lastname;

    private String nickname;

    private Date dateOfBirth;

    private String title;

    private String shortDescription;

    private String longDescription;

    @JsonBackReference
    @ToString.Exclude
    @OneToOne()
    private Client client;
}
