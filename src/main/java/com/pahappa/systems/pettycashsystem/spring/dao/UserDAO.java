package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        if (sessionFactory == null) {
            System.out.println("SessionFactory is null");
        }
        return sessionFactory.getCurrentSession();
    }

    public List<User> getAllUsers() {
        return getCurrentSession().createQuery("from User", User.class).list();
    }

    public User getUserById(Long id) {
        return getCurrentSession().get(User.class, id);
    }

    @Transactional
    public void createUser(User user) {
        getCurrentSession().save(user);
    }

    @Transactional
    public void updateUser(User user) {
        getCurrentSession().update(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = getUserById(id);
        if (user != null) {
            getCurrentSession().delete(user);
        }
    }
}
