package service.serviceImpl;

import model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.TransactionRepository;
import service.TransactionService;

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
