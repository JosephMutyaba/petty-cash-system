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
import java.util.stream.Collectors;

@Named
@ViewScoped
@Component
public class GetEmployees implements Serializable {

    @Autowired
    private UserService userService;

    private List<User> users;

    private String searchTerm;

    @PostConstruct
    public void init() {
        users = userService.getAllUsers();
    }

    public List<User> getUsers() {
        users = userService.getAllUsers();
        filterUsers();
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = userService.getAllUsers();
    }

//    public List<User> retrieveAllEmployees() {
//        return userService.getAllUsers();
//    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void filterUsers() {
        if (searchTerm == null || searchTerm.isEmpty()) {
            users=userService.getAllUsers();
        } else {
            users = users.stream()
                    .filter(user ->
                            user.getFirstname().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(user.getUsername()).toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(user.getLastname()).toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(user.getEmail()).toLowerCase().contains(searchTerm.toLowerCase()))
                    .collect(Collectors.toList());
        }
    }

}
