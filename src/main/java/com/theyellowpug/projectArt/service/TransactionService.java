package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Transaction;
import com.theyellowpug.projectArt.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public String createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        return "Transaction: " + transaction + " successfully created";
    }
}
