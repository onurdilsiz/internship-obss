package com.example.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.servlet.annotation.WebServlet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


@WebService
public class UserServiceImpl implements UserService {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @WebMethod
    public User getUser(String username) {

        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, username);
        }
    }
    @WebMethod

    public void addUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    @WebMethod

    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    @WebMethod

    public void deleteUser(String username) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, username);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
