package com.theyellowpug.projectArt.entity;

import com.theyellowpug.projectArt.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Orderr {
    @Id
    @GeneratedValue
    private Long id;

    private Long artistId;

    private Long customerId;

    private Long transactionId;

    private OrderStatus status;
}
