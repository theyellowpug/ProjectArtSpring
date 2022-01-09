package com.theyellowpug.projectArt.controller;

import com.theyellowpug.projectArt.entity.Transaction;
import com.theyellowpug.projectArt.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/byCustomerId")
    public ResponseEntity<List<Transaction>> getAllTransactionsByCustomerId(@RequestParam("customerId") Long customerId) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByCustomerId(customerId));
    }

    @GetMapping("/byArtistId")
    public ResponseEntity<List<Transaction>> getAllTransactionsByArtistId(@RequestParam("artistId") Long artistId) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByArtistId(artistId));
    }

    @PostMapping("/")
    public ResponseEntity<String> createTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.createTransaction(transaction));
    }
}
