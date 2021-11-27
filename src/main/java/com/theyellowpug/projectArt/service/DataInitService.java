package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.entity.Comment;
import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.entity.Profile;
import com.theyellowpug.projectArt.model.ProductType;
import com.theyellowpug.projectArt.model.UserRole;
import com.theyellowpug.projectArt.repository.ClientRepository;
import com.theyellowpug.projectArt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataInitService implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        List<Product> productListOfBarna = new ArrayList<>();
        List<Comment> commentListOfBarna = new ArrayList<>();

        Product product1 = Product.builder()
                .name("test product 1")
                .productType(ProductType.ITEM)
                .price(2500L)
                .description("description of test product1")
                .build();

        Product product2 = Product.builder()
                .name("test product 2")
                .productType(ProductType.ITEM)
                .price(10000L)
                .description("description of test product2")
                .build();

        Product product3 = Product.builder()
                .name("test service")
                .productType(ProductType.SERVICE)
                .price(23990L)
                .description("description of test service")
                .build();

        productListOfBarna.add(product1);
        productListOfBarna.add(product2);
        productListOfBarna.add(product3);

        Comment comment1 = Comment.builder().text("első").product(product1).build();
        Comment comment2 = Comment.builder().text("come to brazil").product(product3).build();

        commentListOfBarna.add(comment1);
        commentListOfBarna.add(comment2);

        Profile profileToBarna = Profile.builder()
                .firstname("Barna")
                .lastname("Holl")
                .nickname("BarniG")
                .title("Struggling DJ & CTO")
                .shortDescription("Kis birtok nagy birtrok barniGvel nem birtok.")
                .longDescription("A írtam egy dnb számot ilyen:clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare clap snare clap clap snare snare snare snare snare")
                .build();

        Client barnaHoll = Client.builder()
                .email("hollbarna@gmail.com")
                .password(passwordEncoder.encode("csokiscsiga9"))
                .role(UserRole.ROLE_CLIENT)
                .profile(profileToBarna)
                .products(productListOfBarna)
                .comments(commentListOfBarna)
                .build();

        Client client = Client.builder()
                .password(passwordEncoder.encode("admin"))
                .email("admin@admin.com")
                .roles(Arrays.stream(UserRole.values()).collect(Collectors.toSet()))
                .build();

        product1.setClient(barnaHoll);
        product2.setClient(barnaHoll);

        profileToBarna.setClient(barnaHoll);

        comment1.setOwner(barnaHoll);
        comment2.setOwner(barnaHoll);

        clientRepository.save(barnaHoll);
        clientRepository.save(client);
    }
}
