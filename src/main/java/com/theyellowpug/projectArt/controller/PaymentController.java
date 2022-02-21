package com.theyellowpug.projectArt.controller;

import com.stripe.exception.StripeException;
import com.theyellowpug.projectArt.entity.Cart;
import com.theyellowpug.projectArt.entity.Orderr;
import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.entity.Transaction;
import com.theyellowpug.projectArt.model.CreatePaymentResponse;
import com.theyellowpug.projectArt.model.OrderStatus;
import com.theyellowpug.projectArt.model.PaymentData;
import com.theyellowpug.projectArt.repository.OrderRepository;
import com.theyellowpug.projectArt.repository.ProductRepository;
import com.theyellowpug.projectArt.service.CartService;
import com.theyellowpug.projectArt.service.OrderService;
import com.theyellowpug.projectArt.service.PaymentService;
import com.theyellowpug.projectArt.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final TransactionService transactionService;
    private final ProductRepository productRepository;
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    private final CartService cartService;

    @PostMapping("/create-payment-intent")
    public CreatePaymentResponse createPaymentIntent(@RequestParam("clientId") Long clientId) throws StripeException {
        return paymentService.createPaymentIntent(clientId);
    }

    @PostMapping("/savePayment")
    public void savePayment(@RequestBody PaymentData paymentData) {
        cartService.createCartHistoryByClientId(paymentData.getCustomerId());

        Cart cart = cartService.getCartByClientId(paymentData.getCustomerId());

        cart.getProductIds().forEach(productId -> {
            Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);

            Transaction transaction = Transaction.builder()
                    .customerId(paymentData.getCustomerId())
                    .artistId(product.getClient().getId())
                    .productId(productId)
                    .amount(product.getPrice())
                    //.date(paymentData.getDate())
                    .paymentIntentId(paymentData.getPaymentIntentId())
                    .status(paymentData.getPaymentIntentStatus())
                    .build();

            Long transactionId = transactionService.createTransaction(transaction);
//todo: if payment status nobueno dont create order.

            Orderr orderr = Orderr.builder()
                    .customerId(paymentData.getCustomerId())
                    .artistId(product.getClient().getId())
                    .transactionId(transactionId)
                    .status(OrderStatus.RECEIVED)
                    .build();

            orderService.createOrder(orderr);
        });

        cartService.emptyCartByClientId(paymentData.getCustomerId());
    }

}
