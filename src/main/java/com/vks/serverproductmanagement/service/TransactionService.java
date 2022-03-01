package service;

import model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);

    List<Transaction> getAllTransaction();

    Long countTransaction();
}
