package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Cart;
import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.repository.CartRepository;
import com.theyellowpug.projectArt.repository.CartHistoryRepository;
import com.theyellowpug.projectArt.repository.ClientRepository;
import com.theyellowpug.projectArt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final CartHistoryRepository cartHistoryRepository;

    public Cart getCartByClientId(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(EntityNotFoundException::new);
        return cartRepository.findByClient(client).orElseThrow(EntityNotFoundException::new);
    }

    public String addProductToCartByClientId(Long clientId, Long productId) {
        Client client = clientRepository.findById(clientId).orElseThrow(EntityNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
        Cart cart = cartRepository.findByClient(client).orElseThrow(EntityNotFoundException::new);

        List<Long> productIds = cart.getProductIds();
        productIds.add(product.getId());
        cart.setProductIds(productIds);
        cart.setLastModification(LocalDateTime.now());

        cartRepository.save(cart);

        return product.getName() + " added to " + client.getEmail() + "'s cart";
    }

    public String emptyCartByClientId(Long clientId) {
        Cart cart = getCartByClientId(clientId);
        cart.setProductIds(Collections.emptyList());
        cartRepository.save(cart);
        return "Cart: " + cart + " is empty";
    }

    public String createCartHistoryByClientId(Long clientId) {
        Cart cart = getCartByClientId(clientId);
        cartHistoryRepository.save(cart);
        return "Cart: " + cart + " saved to CartHistory";
    }


}
