package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Orderr;
import com.theyellowpug.projectArt.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void createOrder(Orderr orderr) {
        orderRepository.save(orderr);
    }

}
