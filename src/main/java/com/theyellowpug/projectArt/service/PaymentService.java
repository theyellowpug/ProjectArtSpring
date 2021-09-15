package com.theyellowpug.projectArt.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.model.CreatePaymentResponse;
import com.theyellowpug.projectArt.model.PaymentData;
import com.theyellowpug.projectArt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final long HUF_TO_FILLER = 100L;

    private final ProductRepository productRepository;

    public CreatePaymentResponse createPaymentIntent(PaymentData paymentData) throws StripeException {
        Stripe.apiKey = "sk_test_51JUq7hLJBL520A9GyU0RJGFoKokympIiTORgnuSOD62yECu7b5YguuDF4N0B01GCdDfXLGvhONfIxOln7cFJZUpM004CM0eUTh";

        Long price = calculatePriceOfProducts(paymentData.getProductIds());

        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("huf")
                .setAmount(price)
                .build();

        PaymentIntent intent = PaymentIntent.create(createParams);
        return new CreatePaymentResponse(intent.getClientSecret());
    }

    private Long calculatePriceOfProducts(List<Long> productIds) {
        long sum = 0L;
        for (Long currentProductId : productIds) {
            Product currentProduct = productRepository.findById(currentProductId).orElseThrow(EntityNotFoundException::new);
            sum += currentProduct.getPrice() * HUF_TO_FILLER;
        }
        return sum;
    }
}
