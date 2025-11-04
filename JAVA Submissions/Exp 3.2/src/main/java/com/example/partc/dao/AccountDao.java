package com.example.partc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.partc.entity.Account;

@Repository
public class AccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Account findById(Long id) {
        return currentSession().get(Account.class, id);
    }

    public void saveOrUpdate(Account account) {
        currentSession().saveOrUpdate(account);
    }
}
