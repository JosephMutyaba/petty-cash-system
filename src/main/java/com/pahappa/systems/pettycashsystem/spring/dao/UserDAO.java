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
    public User getUserById(Long userId) {
        return getCurrentSession().get(User.class, userId);
    }

    // Read operation: Get all users
    public List<User> getAllUsers() {
        return getCurrentSession().createQuery("select u FROM User u left join fetch u.roles ORDER BY u.id DESC", User.class).list();
    }

    // Update operation
    public void updateUser(User user) {
        getCurrentSession().update(user);
    }

    // Delete operation
    public void deleteUser(Long userId) {
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
        return (User) getCurrentSession().createQuery("select u from User u join u.roles r where u.username = :username and r.name = :role")
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
            return getCurrentSession().createQuery("select u FROM User u join u.roles r WHERE r=:role",User.class)
                    .setParameter("role", role)
                    .getResultList();
        } else {
            return Collections.emptyList(); // Return an empty list if the role is not found
        }
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        return (User) getCurrentSession().createQuery("from User where username=:username and Password=:password")
                .setParameter("username", username)
                .setParameter("password", password)
                .uniqueResult();
    }

    public boolean deleteAllUsers() {
        try {
            getCurrentSession().createQuery("delete from User").executeUpdate();
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public User getUserByUsername(String username) {
        return (User) getCurrentSession()
                .createQuery("from User where username=:username")
                .setParameter("username", username)
                .uniqueResult();
    }

    public User getUserByUserEmail(String email) {
        return (User) getCurrentSession()
                .createQuery("from User where Email=:userEmail")
                .setParameter("userEmail", email)
                .uniqueResult();
    }
}
