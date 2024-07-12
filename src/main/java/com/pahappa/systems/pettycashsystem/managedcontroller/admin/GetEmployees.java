package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Component
public class GetEmployees implements Serializable {

    @Autowired
    private UserService userService;

    private List<User> allEmployees;

    public List<User> getAllEmployees() {
        return allEmployees;
    }

    public void setAllEmployees(List<User> allEmployees) {
        this.allEmployees = allEmployees;
    }

    @PostConstruct
    public void init() {
        allEmployees = userService.getAllUsers();
    }
}
