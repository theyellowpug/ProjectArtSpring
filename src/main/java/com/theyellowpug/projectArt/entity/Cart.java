package com.theyellowpug.projectArt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

    @JsonBackReference
    @ToString.Exclude
    @OneToOne()
    private Client client;

    @ElementCollection
    @Singular
    private List<Long> productIds;

    private LocalDateTime lastModification;
}
