package com.theyellowpug.projectArt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentData {
    private Long customerId;

    private String paymentIntentId;

    private String paymentIntentStatus;

    private Date date;
}
