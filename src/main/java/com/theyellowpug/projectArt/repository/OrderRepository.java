package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
