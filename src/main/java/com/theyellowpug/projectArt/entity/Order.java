package com.theyellowpug.projectArt.entity;

import com.theyellowpug.projectArt.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private Transaction transaction;

    private Long artistId;

    private Long customerId;

    private Long amount;

    private Long productId;

    private OrderStatus status;

    private Date date;
}
