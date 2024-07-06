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


    public void createUser(User user) {
        userDAO.createUser(user);
    }

}
