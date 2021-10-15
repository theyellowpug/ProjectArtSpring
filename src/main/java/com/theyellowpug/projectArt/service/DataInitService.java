package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.entity.Profile;
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

        List<Product> productListOfBarna = new ArrayList<>();
        List<com.theyellowpug.projectArt.entity.Service> serviceListOfBarna = new ArrayList<>();

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

        com.theyellowpug.projectArt.entity.Service service1 = com.theyellowpug.projectArt.entity.Service.builder()
                .name("test service")
                .price(1213L)
                .description("description of test service")
                .build();

        serviceListOfBarna.add(service1);

        Profile profileToBarna = Profile.builder()
                .name("BarniG")
                .title("Struggling DJ & CTO")
                .shortDescription("Kis birtok nagy birtrok barniGvel nem birtok.")
                .longDescription("A írtam egy dnb számot ilyen:clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare snare snare snare snare")
                .build();


        Client barnaHoll = Client.builder()
                .username("Holl Barna")
                .email("hollbarna@gmail.com")
                .password("csokiscsiga9")
                .profile(profileToBarna)
                .products(productListOfBarna)
                .services(serviceListOfBarna)
                .build();

        product1.setClient(barnaHoll);
        product2.setClient(barnaHoll);

        service1.setClient(barnaHoll);

        profileToBarna.setClient(barnaHoll);

        clientRepository.save(barnaHoll);
    }
}
