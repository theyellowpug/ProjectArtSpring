package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.model.ProductModel;
import com.theyellowpug.projectArt.repository.ClientRepository;
import com.theyellowpug.projectArt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Product> getAllProductsByClientId(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return client.getProducts();
    }

    public String createProduct(Long clientId, ProductModel product) {
        Client client = clientRepository.findById(clientId).orElseThrow(EntityNotFoundException::new);

        Product newProduct = Product.builder()
                .productType(product.getProductType())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();

        List<Product> products = client.getProducts();
        products.add(newProduct);

        client.setProducts(products);
        clientRepository.save(client);

        return "Product was successfully added to client: " + client.getUsername();
    }
}
