package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.Cart;
import com.theyellowpug.projectArt.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByClient(Client client);
}
