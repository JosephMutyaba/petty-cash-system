package com.pahappa.systems.pettycashsystem.managedcontroller.login;

import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
@Component
public class LoginBean implements Serializable {
    private final UserService userService;

    @Autowired
    public LoginBean(UserService userService) {
        this.userService = userService;
    }

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String loginUser(){
        User user =userService.findUserByUsernameAndPassword(username, password);
//        System.out.println("User: "+user);
        if (user == null) {
            System.out.println("user not found");
            return "/pages/auth/login.xhtml?faces-redirect=true";
        }else {
            if (user.getRole().getName().equals("ADMIN")) {
                System.out.println("Redirect to admin page");
                return "/pages/adminpages/admin-dashboard.xhtml?faces-redirect=true";
            }
            else return null;
        }
    }
}
