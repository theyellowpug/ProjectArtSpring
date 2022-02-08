package com.theyellowpug.projectArt.controller;

import com.theyellowpug.projectArt.entity.Cart;
import com.theyellowpug.projectArt.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/")
    public ResponseEntity<Cart> getCartByClientId(@RequestParam("clientId") Long clientId) {
        return ResponseEntity.ok(cartService.getCartByClientId(clientId));
    }

    @PutMapping("/addProductToCartByClientId")
    public ResponseEntity<String> addProductToCartByClientId(@RequestParam("clientId") Long clientId, @RequestParam("productId") Long productId) {
        return ResponseEntity.ok(cartService.addProductToCartByClientId(clientId, productId));
    }

}
