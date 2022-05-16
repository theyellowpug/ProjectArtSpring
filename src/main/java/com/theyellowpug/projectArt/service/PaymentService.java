package com.theyellowpug.projectArt.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.theyellowpug.projectArt.entity.Cart;
import com.theyellowpug.projectArt.entity.Orderr;
import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.entity.Transaction;
import com.theyellowpug.projectArt.model.CreatePaymentResponse;
import com.theyellowpug.projectArt.model.OrderStatus;
import com.theyellowpug.projectArt.model.PaymentData;
import com.theyellowpug.projectArt.model.ProductStatus;
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
    private final CartService cartService;
    private final TransactionService transactionService;
    private final OrderService orderService;

    public CreatePaymentResponse createPaymentIntent(Long customerId) throws StripeException {
        Stripe.apiKey = "sk_test_51JUq7hLJBL520A9GyU0RJGFoKokympIiTORgnuSOD62yECu7b5YguuDF4N0B01GCdDfXLGvhONfIxOln7cFJZUpM004CM0eUTh";

        Long price = calculatePriceOfProducts(cartService.getCartByClientId(customerId).getProductIds());

        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("huf")
                .setAmount(price)
                .build();

        PaymentIntent intent = PaymentIntent.create(createParams);
        return new CreatePaymentResponse(intent.getClientSecret());
    }

    public void savePayment(PaymentData paymentData) {
        Cart cart = cartService.getCartByClientId(paymentData.getCustomerId());

        cart.getProductIds().forEach(productId -> {
            Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);

            Transaction transaction = Transaction.builder()
                    .customerId(paymentData.getCustomerId())
                    .artistId(product.getOwner().getId())
                    .productId(productId)
                    .amount(product.getPrice())
                    //.date(paymentData.getDate())
                    .paymentIntentId(paymentData.getPaymentIntentId())
                    .status(paymentData.getPaymentIntentStatus())
                    .build();

            Long transactionId = transactionService.createTransaction(transaction);

            /*if (!paymentData.getPaymentIntentStatus().equals("SUCCESS")) {
                return;
            }*/
            Orderr orderr = Orderr.builder()
                    .customerId(paymentData.getCustomerId())
                    .artistId(product.getOwner().getId())
                    .transactionId(transactionId)
                    .status(OrderStatus.RECEIVED)
                    .build();

            orderService.createOrder(orderr);

            if (product.getProductStatus() == ProductStatus.IN_CART) {
                product.setProductStatus(ProductStatus.SOLD);
            } else if (product.getProductStatus() == ProductStatus.LIMITED) {
                product.setQuantity(product.getQuantity() - 1);
            }
            productRepository.save(product);
        });

        cartService.createCartHistoryByClientId(paymentData.getCustomerId());
        cartService.emptyCartByClientId(paymentData.getCustomerId());
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
