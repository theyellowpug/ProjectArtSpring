package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.*;
import com.theyellowpug.projectArt.model.ProductStatus;
import com.theyellowpug.projectArt.model.ProductType;
import com.theyellowpug.projectArt.model.UserRole;
import com.theyellowpug.projectArt.repository.ClientRepository;
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
    private final ClientRepository clientRepository;
    private final ProductTagRepository productTagRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        productTagRepository.saveAndFlush(ProductTag.builder().name("Painting").build());
        productTagRepository.saveAndFlush(ProductTag.builder().name("DigitalPainting").build());
        productTagRepository.saveAndFlush(ProductTag.builder().name("Abstract").build());

        List<Product> productListOfAdmin = new ArrayList<>();
        Set<Product> wishList = new HashSet<>();
        List<Comment> commentListOfAdmin = new ArrayList<>();
        List<ProductTag> productTagList = new ArrayList<>();
        List<ProductTag> productTagList2 = new ArrayList<>();

        productTagList.add(productTagRepository.findByName("Painting").orElseThrow(EntityNotFoundException::new));
        productTagList.add(productTagRepository.findByName("Abstract").orElseThrow(EntityNotFoundException::new));
        productTagList2.add(productTagRepository.findByName("DigitalPainting").orElseThrow(EntityNotFoundException::new));
        productTagList2.add(productTagRepository.findByName("Abstract").orElseThrow(EntityNotFoundException::new));

        Product product1 = Product.builder()
                .name("Unique product")
                .productType(ProductType.ITEM)
                .price(2500L)
                .description("Description of unique product")
                .productTags(productTagList)
                .productStatus(ProductStatus.AVAILABLE)
                .build();

        Product product2 = Product.builder()
                .name("Limited product")
                .productType(ProductType.ITEM)
                .price(10000L)
                .description("Description of limited test product")
                .productTags(productTagList2)
                .productStatus(ProductStatus.LIMITED)
                .quantity(10L)
                .build();

        Product product3 = Product.builder()
                .name("Unlimited product")
                .productType(ProductType.ITEM)
                .price(10000L)
                .description("Description of unlimited test product")
                .productTags(productTagList)
                .productStatus(ProductStatus.UNLIMITED)
                .build();

        productListOfAdmin.add(product1);
        productListOfAdmin.add(product2);
        productListOfAdmin.add(product3);

        Comment comment1 = Comment.builder().text("első").product(product1).build();
        Comment comment2 = Comment.builder().text("come to brazil").product(product2).build();

        commentListOfAdmin.add(comment1);
        commentListOfAdmin.add(comment2);

        Profile profileToAdmin = Profile.builder()
                .firstname("Admin")
                .lastname("Admin")
                .nickname("Admin")
                .title("Admin")
                .shortDescription("Admin")
                .longDescription("Admin")
                .build();


        Client admin = Client.builder()
                .password(passwordEncoder.encode("admin"))
                .email("admin@admin.com")
                .roles(Arrays.stream(UserRole.values()).collect(Collectors.toSet()))
                .profile(profileToAdmin)
                .isArtist(true)
                .comments(commentListOfAdmin)
                .products(productListOfAdmin)
                .wishList(wishList)
                .build();

        profileToAdmin.setClient(admin);

        clientRepository.save(admin);

    }
}
