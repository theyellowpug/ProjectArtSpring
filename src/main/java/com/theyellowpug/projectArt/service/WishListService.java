package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Client;
import com.theyellowpug.projectArt.entity.Product;
import com.theyellowpug.projectArt.repository.ClientRepository;
import com.theyellowpug.projectArt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public Set<Product> getWishListProductsByClientId(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return client.getWishList();
    }

    public String addProductIdToWishListByClientId(Long clientId, Long productId) {
        Client client = clientRepository.findById(clientId).orElseThrow(EntityNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);

        Set<Client> customers = product.getCustomers();
        customers.add(client);
        product.setCustomers(customers);

        productRepository.save(product);

        return product + "has been added to" + client;
    }

    public void removeProductIdFromWishListByClientId(Long clientId, Long productId) {
        Client client = clientRepository.findById(clientId).orElseThrow(EntityNotFoundException::new);
        Set<Product> wishlist = client.getWishList();
        Product product = productRepository.getById(productId);
        wishlist.remove(product);
        client.setWishList(wishlist);
        clientRepository.save(client);
    }
}
