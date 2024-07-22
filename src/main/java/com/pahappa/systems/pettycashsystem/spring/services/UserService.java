package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.exceptions.NullFieldException;
import com.pahappa.systems.pettycashsystem.managedcontroller.admin.Constants;
import com.pahappa.systems.pettycashsystem.spring.dao.UserDAO;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    private Constants con;

    @Autowired
    private EmailService emailService;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Create operation
    public void createUser(User user) throws Exception {
        validateUser(user, "create");
        userDAO.createUser(user);

        // Send registration email
        String subject = "Registration Successful";
        String message = "Dear " + user.getFirstname() + ",\n\n" +
                "Your registration is successful.Use the following to login to your account\n"
                +"Username : " + user.getUsername() + "\n" +
                "Password : " + user.getPassword() + "\n" +
                "Regards,\nAdmin";
        emailService.sendSimpleMessage(user.getEmail(), subject, message);
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
    public void updateUser(User user) throws Exception {
        validateUser(user, "update");
        userDAO.updateUser(user);

        // Send registration email
        String subject = "Registration Successful";
        String message = "Dear " + user.getFirstname() + ",\n\n" +
                "Your registration is successful.Use the following to login to your account\n"
                +"Username : " + user.getUsername() + "\n" +
                "Password : " + user.getPassword() + "\n" +
                "Regards,\nAdmin";
//        emailService.sendSimpleMessage(user.getEmail(), subject, message);





        // Send confirmation email
        String to = user.getEmail();
//        String subject = "Welcome to Our Service";
//        String text = "Dear " + user.getFirstname() + ",\n\nThank you for registering!";
        emailService.sendSimpleMessage(to, subject, message);
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


    public void validateUser(User user, String action) throws Exception {
        if (user.getUsername() == null || user.getPassword() == null|| user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            throw new NullPointerException("All fields are required");
        }

        if (!user.getFirstname().matches(con.getNameRegex()) || !user.getLastname().matches(con.getNameRegex()))
            throw new Exception("A name can have letters only!");

        if (user.getUsername().length()<4) {
            throw new NullFieldException("Username should be at least 4 characters");
        }

        if (!user.getUsername().matches(con.getUsernameRegex()))
            throw new Exception("A username can only contain letters(a-z,A-Z) numbers(0-9) and underscores(_).");

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

        if (!user.getEmail().matches(con.getEmailRegex())) {
            throw new NullPointerException("Sorry, only letters (a-z), numbers (0-9), and periods (.) are allowed.");
        }

        if (!user.getPassword().matches(con.getPasswordRegex())) {
            throw new NullPointerException("Password should contain at least contain a number, upper, lowercase and special characters. Minimum length of 8");
        }

        if (action.equals("create")) {
            if (userDAO.getUserByUserEmail(user.getEmail())!=null) {
                throw new NullFieldException("Email already exists, try a different one");
            }
        }

    }

    public User getByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}








