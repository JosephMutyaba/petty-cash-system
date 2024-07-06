package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.spring.dao.UserDAO;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService() {
        this.userDAO = new UserDAO();
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    public void createUser(User user) {
        userDAO.createUser(user);
    }

    public void updateUser(Long id, User userDetails) {
        User user = userDAO.getUserById(id);

        if (user != null) {
            user.setFirstname(userDetails.getFirstname());
            user.setLastname(userDetails.getLastname());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setRole(userDetails.getRole());
            user.setUsername(userDetails.getUsername());
            user.setRequisition(userDetails.getRequisition());

            userDAO.updateUser(user);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }
}
