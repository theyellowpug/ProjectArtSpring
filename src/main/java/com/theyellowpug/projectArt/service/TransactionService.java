package com.theyellowpug.projectArt.service;

import com.theyellowpug.projectArt.entity.Transaction;
import com.theyellowpug.projectArt.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Long createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        return transactionRepository.findByPaymentIntentId(transaction.getPaymentIntentId()).orElseThrow(EntityNotFoundException::new).getId();
    }

    public List<Transaction> getAllTransactionsByCustomerId(Long customerId) {
        return transactionRepository.findAllByCustomerId(customerId);
    }

    public List<Transaction> getAllTransactionsByArtistId(Long artistId) {
        return transactionRepository.findAllByArtistId(artistId);
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
