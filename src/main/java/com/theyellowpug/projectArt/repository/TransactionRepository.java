package com.theyellowpug.projectArt.repository;

import com.theyellowpug.projectArt.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
