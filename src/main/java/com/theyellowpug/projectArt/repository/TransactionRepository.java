package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByCustomerId(Long customerId);

    Optional<Transaction> findByPaymentIntentId(String paymentIntentId);

}
