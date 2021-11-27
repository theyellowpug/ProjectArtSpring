package com.theyellowpug.projectArt.controller;

import com.theyellowpug.projectArt.entity.ProductTag;
import com.theyellowpug.projectArt.service.ProductTagServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/productTag")
public class ProductTagController {

    private final ProductTagServices productTagServices;

    @GetMapping("/all")
    public ResponseEntity<List<ProductTag>> getAllProductTags() {
        return ResponseEntity.ok(productTagServices.getAllProductTags());
    }
}
