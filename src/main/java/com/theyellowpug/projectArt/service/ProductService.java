package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.dTO.ProductDTO;
import com.theyellowpug.projectArt.dTO.ProductTagNamesDTO;
import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.model.ProductModel;
import com.theyellowpug.projectArt.model.ProductType;
import com.theyellowpug.projectArt.repository.ClientRepository;
import com.theyellowpug.projectArt.repository.ProductRepository;
import com.theyellowpug.projectArt.repository.ProductTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductTagRepository productTagRepository;
    private final ClientRepository clientRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductDTO getProductById(Long id) {
        return createProductDTOFromProductById(id);
    }

    public List<ProductDTO> getAllProductsByClientId(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return client.getProducts().stream().map(product -> createProductDTOFromProductById(product.getId())).collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByProductType(ProductType productType, Long numberOfPages, Long numberOfProducts) {
        Pageable pageable = PageRequest.of(numberOfPages.intValue(), numberOfProducts.intValue());
        List<Product> products = productRepository.findAllByProductType(productType, pageable);

        return products.stream().map(product -> createProductDTOFromProductById(product.getId())).collect(Collectors.toList());
    }

    public String createProduct(Long clientId, ProductModel product) {
        Client client = clientRepository.findById(clientId).orElseThrow(EntityNotFoundException::new);

        Product newProduct = Product.builder()
                .productType(product.getProductType())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .owner(clientRepository.findById(clientId).orElseThrow(EntityNotFoundException::new))
                .build();

        List<Product> products = client.getProducts();
        products.add(newProduct);

        client.setProducts(products);
        clientRepository.save(client);

        return "Product was successfully added to client: " + client.getEmail();
    }

    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "Product(with id:" + id + ") was successfully removed";
    }

    public List<Product> getAllByProductTypeAndProductTags(ProductType productType, ProductTagNamesDTO productTagNames) {
        Set<Product> products = new HashSet<>();

        productTagNames.getNames().forEach(name -> products.addAll(productRepository.findAllByProductTypeAndProductTags(productType, productTagRepository.findByName(name).orElseThrow(EntityNotFoundException::new))));
        return new ArrayList<>(products);
    }

    public List<Product> getAllByProductTypeAndNameContains(ProductType productType, String name) {
        return productRepository.findAllByProductTypeAndNameContains(productType, name);
    }

    private ProductDTO createProductDTOFromProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Long ownerId = product.getOwner().getId();

        return ProductDTO.builder()
                .id(product.getId())
                .ownerId(ownerId)
                .productType(product.getProductType())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .productStatus(product.getProductStatus())
                .quantity(product.getQuantity())
                .images(product.getImages())
                .comments(product.getComments())
                .productTags(product.getProductTags())
                .build();
    }
}
