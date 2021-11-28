package com.theyellowpug.projectArt.controller;

import com.theyellowpug.projectArt.dTO.ProductTagNamesDTO;
import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.model.ProductModel;
import com.theyellowpug.projectArt.model.ProductType;
import com.theyellowpug.projectArt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/")
    public ResponseEntity<Product> getProductById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/byProductType")
    public ResponseEntity<List<Product>> getProductsByProductType(@RequestParam("productType") ProductType productType,
                                                                  @RequestParam("numberOfPages") Long numberOfPages,
                                                                  @RequestParam("numberOfProducts") Long numberOfProducts
    ) {
        return ResponseEntity.ok(productService.getProductsByProductType(productType, numberOfPages, numberOfProducts));
    }

    @PostMapping("/getAllByProductTags")
    public ResponseEntity<List<Product>> getAllByProductTypeAndProductTags(@RequestParam("productType") ProductType productType, @RequestBody ProductTagNamesDTO productTagNames) {
        return ResponseEntity.ok(productService.getAllByProductTypeAndProductTags(productType, productTagNames));
    }

    @GetMapping("/allByClientId")
    public ResponseEntity<List<Product>> getAllProductsByClientId(@RequestParam("clientId") Long clientId) {
        return ResponseEntity.ok(productService.getAllProductsByClientId(clientId));
    }

    @PostMapping("/")
    public ResponseEntity<String> createProduct(@RequestParam("clientId") Long clientId, @RequestBody ProductModel product) {
        return ResponseEntity.ok(productService.createProduct(clientId, product));
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteProduct(@RequestParam("id") Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
