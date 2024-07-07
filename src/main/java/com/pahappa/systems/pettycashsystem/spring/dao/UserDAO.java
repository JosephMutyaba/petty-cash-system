package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }
}














//@Repository
//public class UserDAO {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//
//        if (sessionFactory == null) {
//            System.out.println("SessionFactory is null");
//
//        }
//    }
//
//
////    private Session getCurrentSession() {
////        if (sessionFactory == null) {
////            System.out.println("SessionFactory is null");
////
////        }
////        return sessionFactory.getCurrentSession();
////    }
//
//    @Transactional
//    public void createUser(User user) {
//        this.sessionFactory.getCurrentSession().save(user);
//    }
//
//    @Transactional
//    public void updateUser(User user) {
//        this.sessionFactory.getCurrentSession().update(user);
//    }
//
//
//}
