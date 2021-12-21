package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.*;
import com.theyellowpug.projectArt.model.ProductType;
import com.theyellowpug.projectArt.model.UserRole;
import com.theyellowpug.projectArt.repository.ClientRepository;
import com.theyellowpug.projectArt.repository.ProductRepository;
import com.theyellowpug.projectArt.repository.ProductTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataInitService implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final ProductTagRepository productTagRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        productTagRepository.saveAndFlush(ProductTag.builder().name("aaaa").build());
        productTagRepository.saveAndFlush(ProductTag.builder().name("abab").build());
        productTagRepository.saveAndFlush(ProductTag.builder().name("abba").build());

        List<Product> productListOfBarna = new ArrayList<>();
        List<Comment> commentListOfBarna = new ArrayList<>();
        List<ProductTag> productTagList = new ArrayList<>();
        List<ProductTag> productTagList2 = new ArrayList<>();

        productTagList.add(productTagRepository.findByName("aaaa").orElseThrow(EntityNotFoundException::new));
        productTagList.add(productTagRepository.findByName("abab").orElseThrow(EntityNotFoundException::new));
        productTagList2.add(productTagRepository.findByName("aaaa").orElseThrow(EntityNotFoundException::new));
        productTagList2.add(productTagRepository.findByName("abba").orElseThrow(EntityNotFoundException::new));

        Product product4 = Product.builder()
                .productType(ProductType.SERVICE)
                .name("Tag test")
                .price(9800L)
                .productTags(productTagList)
                .build();
        productRepository.save(product4);


        Product product1 = Product.builder()
                .name("test product 1")
                .productType(ProductType.ITEM)
                .price(2500L)
                .description("description of test product1")
                .productTags(productTagList)
                .build();

        Product product2 = Product.builder()
                .name("test product 2")
                .productType(ProductType.ITEM)
                .price(10000L)
                .description("description of test product2")
                .productTags(productTagList2)
                .build();

        Product product3 = Product.builder()
                .name("test service")
                .productType(ProductType.SERVICE)
                .price(23990L)
                .description("description of test service")
                .productTags(productTagList2)
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

        Profile profileToAdmin = Profile.builder()
                .firstname("Admin")
                .lastname("Admin")
                .nickname("Admin")
                .title("Admin")
                .shortDescription("Admin")
                .longDescription("Admin")
                .build();

        Client barnaHoll = Client.builder()
                .email("hollbarna@gmail.com")
                .password(passwordEncoder.encode("csokiscsiga9"))
                .role(UserRole.ROLE_CLIENT)
                .isArtist(false)
                .profile(profileToBarna)
                .products(productListOfBarna)
                .comments(commentListOfBarna)
                .build();

        Client client = Client.builder()
                .password(passwordEncoder.encode("admin"))
                .email("admin@admin.com")
                .roles(Arrays.stream(UserRole.values()).collect(Collectors.toSet()))
                .profile(profileToAdmin)
                .isArtist(true)
                .build();

        product1.setClient(barnaHoll);
        product2.setClient(barnaHoll);

        profileToBarna.setClient(barnaHoll);
        profileToAdmin.setClient(client);

        comment1.setOwner(barnaHoll);
        comment2.setOwner(barnaHoll);

        clientRepository.save(barnaHoll);
        clientRepository.save(client);

    }
}
