package com.vks.serverproductmanagement.service.serviceImpl;

import com.vks.serverproductmanagement.model.Transaction;
import com.vks.serverproductmanagement.repository.TransactionRepository;
import com.vks.serverproductmanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TansactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction saveTransaction(Transaction transaction){
return  transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public Long countTransaction() {
        return transactionRepository.count();
    }
}
