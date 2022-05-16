package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartHistoryRepository extends JpaRepository<Cart, Long> {
}
