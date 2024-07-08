package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
@Component
public class GetEmployees implements Serializable {
    private UserService userService;

    @Autowired
    public GetEmployees(UserService userService) {
        this.userService = userService;
    }
    public List<User> retrieveAllEmployees() {
        return userService.getAllUsers();
    }
}
