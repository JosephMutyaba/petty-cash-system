package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@Component
public class DeleteUser implements Serializable {
    @Autowired
    private UserService userService;

    private User user;
    @PostConstruct
    public void init() {
        user=new User();
    }

    public void deleteUser() {
        userService.deleteUser(user.getId());
    }

    public void selectedUser(User userSelected) {
        user=userSelected;
    }

    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }





}
