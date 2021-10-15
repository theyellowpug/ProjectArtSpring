package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.repository.ClientRepository;
import com.theyellowpug.projectArt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataInitService implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    @Override
    public void run(String... args) {

        List<Product> productListOfBarna = new ArrayList<Product>();

        Product product1 = Product.builder()
                .name("test product 1")
                .price(2500L)
                .description("description of test product1")
                .build();

        Product product2 = Product.builder()
                .name("test product 2")
                .price(10000L)
                .description("description of test product2")
                .build();

        productListOfBarna.add(product1);
        productListOfBarna.add(product2);

        Client barnaHoll = Client.builder()
                .username("Holl Barna")
                .email("hollbarna@gmail.com")
                .password("csokiscsiga9")
                .shortDescription("Kis birtok nagy bitrok barniGvel nem bírtok.")
                .longDescription("A írtam egy dnb számot ilyen: clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare snare snare snare snare")
                .products(productListOfBarna)
                .build();

        product1.setClient(barnaHoll);
        product2.setClient(barnaHoll);

        clientRepository.save(barnaHoll);


    }
}
