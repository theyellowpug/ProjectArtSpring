package com.theyellowpug.projectArt.controller;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.theyellowpug.projectArt.model.CreatePayment;
import com.theyellowpug.projectArt.model.CreatePaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private Long calculateOrderAmount(Object[] items) {
        // Replace this constant with a calculation of the order's amount
        // Calculate the order total on the server to prevent
        // users from directly manipulating the amount on the client
        return 1400L;
    }

    @PostMapping("/create-payment-intent")
    public CreatePaymentResponse createPaymentIntent() throws StripeException {
    //@RequestBody CreatePayment createPayment
        Stripe.apiKey = "sk_test_51JUq7hLJBL520A9GyU0RJGFoKokympIiTORgnuSOD62yECu7b5YguuDF4N0B01GCdDfXLGvhONfIxOln7cFJZUpM004CM0eUTh";

        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("huf")
                .setAmount(2000*100L)
                .build();

        PaymentIntent intent = PaymentIntent.create(createParams);
        return new CreatePaymentResponse(intent.getClientSecret());
    }

}
