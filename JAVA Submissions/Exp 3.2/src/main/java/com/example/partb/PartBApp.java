package com.example.partb;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.partb.entity.StudentEntity;
import com.example.partb.util.HibernateUtil;

public class PartBApp {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        // Create
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        StudentEntity s = new StudentEntity("Bob", "bob@example.com");
        session.save(s);
        tx.commit();
        session.close();

        System.out.println("Created student with id=" + s.getId());

        // Read
        session = sf.openSession();
        List<StudentEntity> list = session.createQuery("from StudentEntity", StudentEntity.class).list();
        System.out.println("All students:");
        for (StudentEntity se : list) {
            System.out.println(se);
        }
        session.close();

        // Update
        session = sf.openSession();
        tx = session.beginTransaction();
        StudentEntity toUpdate = session.get(StudentEntity.class, s.getId());
        toUpdate.setName("Robert");
        session.update(toUpdate);
        tx.commit();
        session.close();

        System.out.println("Updated student: " + toUpdate);

        // Delete
        session = sf.openSession();
        tx = session.beginTransaction();
        StudentEntity toDelete = session.get(StudentEntity.class, s.getId());
        session.delete(toDelete);
        tx.commit();
        session.close();

        System.out.println("Deleted student id=" + s.getId());

        HibernateUtil.shutdown();
    }
}
