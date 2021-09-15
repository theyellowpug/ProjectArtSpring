package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataInitService implements CommandLineRunner {
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        productRepository.save(
                Product.builder()
                        .name("test product 1")
                        .price(2500L)
                        .build());
        productRepository.save(
                Product.builder()
                        .name("test product 2")
                        .price(10000L)
                        .build());
    }
}
