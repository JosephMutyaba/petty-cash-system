package com.pahappa.systems.pettycashsystem.managedcontroller.login;

import com.pahappa.systems.pettycashsystem.spring.models.Role;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
@Component
public class LoginBean implements Serializable {
    private final UserService userService;
    private User loggedInUser;

    @Autowired
    public LoginBean(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        loggedInUser=new User();
    }

    private String username;
    private String userPassword;

    private String firstname;
    private String lastname;
    private String email;
    private Role role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String password) {
        this.userPassword = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String loginUser(){
         loggedInUser =userService.findUserByUsernameAndPassword(username, userPassword);
//        System.out.println("User: "+user);
        if (loggedInUser == null) {
            System.out.println("user not found");
            return "/pages/auth/login.xhtml?faces-redirect=true";
        }else {
            this.firstname=loggedInUser.getFirstname();
            this.lastname=loggedInUser.getLastname();
            this.email=loggedInUser.getEmail();
            this.role=loggedInUser.getRole();
            this.username=loggedInUser.getUsername();
            this.userPassword =loggedInUser.getPassword();

            if (loggedInUser.getRole().getName().equals("ADMIN")) {
                System.out.println("Redirect to admin page");
                return "/pages/adminpages/admin-dashboard.xhtml?faces-redirect=true";
            }
            else return null;
        }
    }

    public String logoutUser(){
        return "/pages/auth/login.xhtml?faces-redirect=true";
    }

    public void updateLoggedInUser(){
        loggedInUser.setFirstname(firstname);
        loggedInUser.setLastname(lastname);
        loggedInUser.setEmail(email);
        loggedInUser.setRole(role);
        loggedInUser.setUsername(username);
        loggedInUser.setPassword(userPassword);
        userService.updateUser(loggedInUser);
    }
}
