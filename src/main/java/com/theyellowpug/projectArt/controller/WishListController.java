package com.theyellowpug.projectArt.controller;

import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/wishlist")
public class WishListController {
    private final WishListService wishListService;

    @GetMapping("/")
    public ResponseEntity<Set<Product>> getWishListProductsByClientId(@RequestParam("id") Long id) {
        return ResponseEntity.ok(wishListService.getWishListProductsByClientId(id));
    }

    @PutMapping("/")
    public ResponseEntity<String> addProductIdToWishListByClientId(@RequestParam("clientId") Long clientId, @RequestParam("productId") Long productId) {
        return ResponseEntity.ok(wishListService.addProductIdToWishListByClientId(clientId, productId));
    }

    @DeleteMapping("/")
    public void removeProductIdFromWishListByClientId(@RequestParam("clientId") Long clientId, @RequestParam("productId") Long productId) {
        wishListService.removeProductIdFromWishListByClientId(clientId, productId);
    }


}
