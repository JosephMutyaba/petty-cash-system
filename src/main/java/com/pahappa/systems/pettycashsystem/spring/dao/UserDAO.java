package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.Role;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.RoleService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
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

    public User getUserUsernameAndRole(String username, String role) {
        return (User) getCurrentSession().createQuery("from User where username = :username and role.name = :role")
                .setParameter("username", username)
                .setParameter("role", role)
                .uniqueResult();
    }

    public List<User> findByRoleName(String roleName) {
        // Fetch the role using a query
        Role role = (Role) getCurrentSession().createQuery("FROM Role WHERE name=:role_name")
                .setParameter("role_name", roleName)
                .uniqueResult(); // Fetch a single result

        if (role != null) {
            // Fetch users with the role
            return getCurrentSession().createQuery("FROM User WHERE role.id=:role_id", User.class)
                    .setParameter("role_id", role.getId())
                    .getResultList();
        } else {
            return Collections.emptyList(); // Return an empty list if the role is not found
        }
    }

}
