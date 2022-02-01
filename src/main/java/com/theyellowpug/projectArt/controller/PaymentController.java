package com.theyellowpug.projectArt.controller;

import com.stripe.exception.StripeException;
import com.theyellowpug.projectArt.model.CreatePaymentResponse;
import com.theyellowpug.projectArt.model.PaymentData;
import com.theyellowpug.projectArt.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create-payment-intent")
    public CreatePaymentResponse createPaymentIntent(@RequestBody PaymentData paymentData) throws StripeException {
        return paymentService.createPaymentIntent(paymentData);
    }

    @PostMapping("/create-payment-intent-with-customerId")
    public CreatePaymentResponse createPaymentIntentWithUserId(@RequestBody PaymentData paymentData, @RequestParam("customerId") Long customerId) throws StripeException {
        return paymentService.createPaymentIntentWithUserId(paymentData, customerId);
    }

}
