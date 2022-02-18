package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Order;
import com.theyellowpug.projectArt.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public String createOrder(Order order) {
        orderRepository.save(order);
        return "Order: " + order + " created";
    }

}
