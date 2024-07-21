package com.pahappa.systems.pettycashsystem.managedcontroller.login;

import com.pahappa.systems.pettycashsystem.spring.models.Role;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

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
        loggedInUser = new User();
    }

    private String username;
    private String userPassword;

    private String firstname;
    private String lastname;
    private String email;
//    private List<Role> userRoles;

    private Double acc_bal;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

//    public List<Role> getUserRoles() {
//        return userRoles;
//    }
//
//    public void setUserRoles(List<Role> userRoles) {
//        this.userRoles = userRoles;
//    }

    public Double getAcc_bal() {
        this.acc_bal = userService.getUserById(loggedInUser.getId()).getAccountBalance();
        return userService.getUserById(loggedInUser.getId()).getAccountBalance();
    }

    public void setAcc_bal(Double acc_bal) {
        this.acc_bal = acc_bal;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String loginUser() {
        loggedInUser = userService.findUserByUsernameAndPassword(username, userPassword);
        if (loggedInUser == null) {
            System.out.println("User not found");
            return "/pages/auth/login.xhtml?faces-redirect=true";
        } else {
            this.email = loggedInUser.getEmail();
//            setUserRoles(loggedInUser.getRoles());
//            this.role =
            this.username = loggedInUser.getUsername();
            this.userPassword = loggedInUser.getPassword();
            this.acc_bal = loggedInUser.getAccountBalance();
            this.firstname = loggedInUser.getFirstname();
            this.lastname = loggedInUser.getLastname();

            // Set loginBean in session
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loginBean", this);

            return "/pages/adminpages/admin-dashboard.xhtml?faces-redirect=true";
        }
    }

    public String logoutUser() {
        loggedInUser = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession(); // Invalidate current session
        return "/pages/auth/login.xhtml?faces-redirect=true"; // Redirect to login page
    }

    public void updateLoggedInUser() {
        loggedInUser.setFirstname(firstname);
        loggedInUser.setLastname(lastname);
        loggedInUser.setEmail(email);
//        loggedInUser.setRole(role);
        loggedInUser.setUsername(username);
        loggedInUser.setPassword(userPassword);
        try {
            userService.updateUser(loggedInUser);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }
}
