package com.theyellowpug.projectArt.controller;

import com.stripe.exception.StripeException;
import com.theyellowpug.projectArt.entity.Cart;
import com.theyellowpug.projectArt.entity.Orderr;
import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.entity.Transaction;
import com.theyellowpug.projectArt.model.CreatePaymentResponse;
import com.theyellowpug.projectArt.model.OrderStatus;
import com.theyellowpug.projectArt.model.PaymentData;
import com.theyellowpug.projectArt.model.ProductStatus;
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

    private final CartService cartService;

    @PostMapping("/create-payment-intent")
    public CreatePaymentResponse createPaymentIntent(@RequestParam("clientId") Long clientId) throws StripeException {
        return paymentService.createPaymentIntent(clientId);
    }

    @PostMapping("/savePayment")
    public void savePayment(@RequestBody PaymentData paymentData) {
        paymentService.savePayment(paymentData);
    }

}
