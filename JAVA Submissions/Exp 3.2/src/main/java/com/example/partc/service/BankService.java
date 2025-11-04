package com.example.partc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.partc.dao.AccountDao;
import com.example.partc.entity.Account;

@Service
public class BankService {

    @Autowired
    private AccountDao accountDao;

    @Transactional
    public void transfer(Long fromId, Long toId, double amount) {
        Account from = accountDao.findById(fromId);
        Account to = accountDao.findById(toId);

        if (from == null || to == null) {
            throw new IllegalArgumentException("Account not found");
        }

        if (from.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds, will rollback");
        }

        from.setBalance(from.getBalance() - amount);
        accountDao.saveOrUpdate(from);

        // Simulate potential failure point
        // if (amount > 5000) throw new RuntimeException("Simulated failure after debit");

        to.setBalance(to.getBalance() + amount);
        accountDao.saveOrUpdate(to);
    }
}
