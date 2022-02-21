package com.theyellowpug.projectArt.controller;

import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.service.CartService;
import com.theyellowpug.projectArt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;

    @GetMapping("/numberOfProductsByClientId")
    public ResponseEntity<Integer> getNumberOfProductsByClientId(@RequestParam("clientId") Long clientId) {
        return ResponseEntity.ok(cartService.getCartByClientId(clientId).getProductIds().size());
    }

    @GetMapping("/productsByClientId")
    public ResponseEntity<List<Product>> getProductsByClientId(@RequestParam("clientId") Long clientId) {
        List<Product> products = cartService.getCartByClientId(clientId).getProductIds().stream().map(id -> productService.getProductById(id)).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @PutMapping("/addProductToCartByClientId")
    public ResponseEntity<String> addProductToCartByClientId(@RequestParam("clientId") Long clientId, @RequestParam("productId") Long productId) {
        return ResponseEntity.ok(cartService.addProductToCartByClientId(clientId, productId));
    }

}
