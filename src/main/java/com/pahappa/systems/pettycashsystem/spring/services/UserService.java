package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.exceptions.NullFieldException;
import com.pahappa.systems.pettycashsystem.spring.dao.UserDAO;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Create operation
    public void createUser(User user) throws NullFieldException {
        validateUser(user, "create");
        userDAO.createUser(user);
    }

    // Read operation: Get user by ID
    public User getUserById(Long userId) {
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
    public void updateUser(User user) throws NullFieldException {
        validateUser(user, "update");
        userDAO.updateUser(user);
    }

    // Delete operation
    public void deleteUser(Long userId) {
        userDAO.deleteUser(userId);
    }

    public List<User> findByRoleName(String role_name) {
        return userDAO.findByRoleName(role_name);
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        return userDAO.findUserByUsernameAndPassword(username, password);
    }

    public boolean deleteAllUsers() {
        return userDAO.deleteAllUsers();
    }


    public void validateUser(User user, String action) throws NullFieldException {
        if (user.getUsername() == null || user.getPassword() == null|| user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            throw new NullPointerException("All fields are required");
        }

        if (user.getUsername().length()<4) {
            throw new NullFieldException("Username should be at least 4 characters");
        }

        if (action.equals("create")) {
            if (userDAO.getUserByUsername(user.getUsername())!=null) {
                throw new NullFieldException("Username already exists, try a different one");
            }
        }

        if (user.getFirstname() == null || user.getLastname() == null|| user.getFirstname().isEmpty() || user.getLastname().isEmpty()) {
            throw new NullPointerException("All fields are required");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new NullPointerException("All fields are required");
        }

        if (!Pattern.matches( "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]+$",user.getEmail())) {
            throw new NullPointerException("Email is not valid");
        }

        if (!Pattern.matches( "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#^{}\\[\\]:;'\"/\\\\,.+=\\-_><])[A-Za-z\\d@$!%*?&#^{}\\[\\]:;'\"/\\\\,.+=\\-_>< ]{8,}$",user.getPassword())) {
            throw new NullPointerException("Password should contain at least contain a number, upper, lowercase and special characters. Minimum length of 8");
        }

    }

    public User getByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}








