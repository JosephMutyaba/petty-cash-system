package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.spring.dao.UserDAO;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Create operation
    public void createUser(User user) {
        userDAO.createUser(user);
    }

    // Read operation: Get user by ID
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    // Read operation: Get user by username
    public User getUserUsernameAndRole(String username, String role) {
        return userDAO.getUserUsernameAndRole(username, role);
    }

    // Read operation: Get all users
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    // Update operation
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    // Delete operation
    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }

    public List<User> findByRoleName(String role_name) {
        return userDAO.findByRoleName(role_name);
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        return userDAO.findUserByUsernameAndPassword(username, password);
    }
}








