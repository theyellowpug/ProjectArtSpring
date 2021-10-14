package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.repository.ClientRepository;
import com.theyellowpug.projectArt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataInitService implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

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

        clientRepository.save(
                Client.builder()
                        .username("Holl Barna")
                        .email("hollbarna@gmail.com")
                        .password("csokiscsiga9")
                        .shortDescription("Kis birtok nagy bitrok barniGvel nem bírtok.")
                        .longDescription("A írtam egy dnb számot ilyen: clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare v clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare snare snare snare snare")
                        .build());

    }
}
