package com.pahappa.systems.pettycashsystem.managedcontroller.login;

import com.pahappa.systems.pettycashsystem.spring.enums.Perm;
import com.pahappa.systems.pettycashsystem.spring.models.Permission;
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
import java.util.Set;

@ManagedBean
@SessionScoped
@Component
public class LoginBean implements Serializable {
    public static final ExternalContext EXTERNAL_CONTEXT = FacesContext.getCurrentInstance().getExternalContext();
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
    private Set<Role> roles;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

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

    public ExternalContext getExternalContext() {return EXTERNAL_CONTEXT;}
    public static void redirect(String url) throws Exception {
        EXTERNAL_CONTEXT.redirect(EXTERNAL_CONTEXT.getRequestContextPath() + url);
    }

    public void loginUser() throws Exception {
        loggedInUser = userService.findUserByUsernameAndPassword(username, userPassword);
        if (loggedInUser == null) {
            System.out.println("User not found");
            redirect("/pages/auth/login.xhtml");
        } else {
            this.email = loggedInUser.getEmail();
            this.roles = loggedInUser.getRoles();
            this.username = loggedInUser.getUsername();
            this.userPassword = loggedInUser.getPassword();
            this.acc_bal = loggedInUser.getAccountBalance();
            this.firstname = loggedInUser.getFirstname();
            this.lastname = loggedInUser.getLastname();
            loadPermissions();

            // Set loginBean in session
            EXTERNAL_CONTEXT.getSessionMap().put("loginBean", this);

            boolean dashboard = false;
            for (Permission p:loggedInUser.getPermissions())
                if (p.getName() == Perm.VIEW_DASHBOARD) {
                    dashboard = true;
                    break;
                }
            if (dashboard)
                redirect("/pages/adminpages/admin-dashboard.xhtml");
            else
                redirect("/pages/adminpages/admin-settings.xhtml");
        }
    }
    public void loadPermissions() {
        userService.loadPermissions(loggedInUser);
    }

    public void logoutUser() throws Exception {
        loggedInUser = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession(); // Invalidate current session
        redirect("/pages/auth/login.xhtml"); // Redirect to login page
    }

    public void updateLoggedInUser() {
        loggedInUser.setFirstname(firstname);
        loggedInUser.setLastname(lastname);
        loggedInUser.setEmail(email);
        loggedInUser.setRoles(roles);
        loggedInUser.setUsername(username);
        loggedInUser.setPassword(userPassword);
        userService.loadPermissions(loggedInUser);
        try {
            userService.updateUser(loggedInUser);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }
}
