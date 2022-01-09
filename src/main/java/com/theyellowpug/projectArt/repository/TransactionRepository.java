package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByCustomerId(Long customerId);

    List<Transaction> findAllByArtistId(Long artistId);
}
