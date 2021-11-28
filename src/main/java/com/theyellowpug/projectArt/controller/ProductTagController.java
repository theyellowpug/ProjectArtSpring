package com.theyellowpug.projectArt.controller;

import com.theyellowpug.projectArt.entity.ProductTag;
import com.theyellowpug.projectArt.service.ProductTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/productTag")
public class ProductTagController {

    private final ProductTagService productTagService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductTag>> getAllProductTags() {
        return ResponseEntity.ok(productTagService.getAllProductTags());
    }

    @GetMapping("/byName")
    public ResponseEntity<List<ProductTag>> getProductTagsByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(productTagService.getProductTagsByName(name));
    }
}
