package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Create operation
    public void createUser(User user) {
        getCurrentSession().save(user);
    }

    // Read operation: Get user by ID
    public User getUserById(int userId) {
        return getCurrentSession().get(User.class, userId);
    }

    // Read operation: Get all users
    public List<User> getAllUsers() {
        return getCurrentSession().createQuery("FROM User", User.class).list();
    }

    // Update operation
    public void updateUser(User user) {
        getCurrentSession().update(user);
    }

    // Delete operation
    public void deleteUser(int userId) {
        User user = getCurrentSession().load(User.class, userId);
        if (user != null) {
            getCurrentSession().delete(user);
        }
    }

    // Utility method to get current session
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
