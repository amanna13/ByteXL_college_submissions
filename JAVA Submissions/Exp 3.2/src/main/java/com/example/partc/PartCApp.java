package com.example.partc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.partc.entity.Account;

public class PartCApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigTx.class);

        SessionFactory sf = ctx.getBean(SessionFactory.class);

        // Create two accounts
        Session s = sf.openSession();
        s.beginTransaction();
        Account a1 = new Account("Carol", 1000.00);
        Account a2 = new Account("Dave", 200.00);
        s.save(a1);
        s.save(a2);
        s.getTransaction().commit();
        s.close();

        System.out.println("Created accounts: " + a1 + " and " + a2);

        // Perform a successful transfer
        com.example.partc.service.BankService bankService = ctx.getBean(com.example.partc.service.BankService.class);
        try {
            bankService.transfer(a1.getId(), a2.getId(), 300.00);
            System.out.println("Transfer succeeded: 300 from Carol to Dave");
        } catch (Exception ex) {
            System.err.println("Transfer failed: " + ex.getMessage());
        }

        // Show balances after transfer
        s = sf.openSession();
        Account updatedA1 = s.get(Account.class, a1.getId());
        Account updatedA2 = s.get(Account.class, a2.getId());
        s.close();

        System.out.println("After transfer: " + updatedA1 + " and " + updatedA2);

        // Attempt a transfer that will fail (insufficient funds) to demonstrate rollback
        try {
            bankService.transfer(a2.getId(), a1.getId(), 10000.00);
            System.out.println("This line should not print (transfer should rollback)");
        } catch (Exception ex) {
            System.err.println("Expected failure and rollback: " + ex.getMessage());
        }

        // Show balances after failed transfer
        s = sf.openSession();
        updatedA1 = s.get(Account.class, a1.getId());
        updatedA2 = s.get(Account.class, a2.getId());
        s.close();

        System.out.println("After failed transfer: " + updatedA1 + " and " + updatedA2);

        ctx.close();
    }
}
