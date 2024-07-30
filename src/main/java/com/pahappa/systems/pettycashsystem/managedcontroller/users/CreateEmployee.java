package com.pahappa.systems.pettycashsystem.managedcontroller.users;

import com.pahappa.systems.pettycashsystem.spring.models.Role;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.RoleService;
import com.pahappa.systems.pettycashsystem.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Named
@ViewScoped
@Component
public class CreateEmployee implements Serializable {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String roleName;
    private List<Role> rolesFromDB;
    private Set<Role> roles;
    private String username;
    private User user;



    @PostConstruct
    public void init() {
        setRolesFromDB();
//        role = new Role();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }


    public List<Role> getRolesFromDB() {
        setRolesFromDB();
        return rolesFromDB;
    }

    public void setRolesFromDB() {
        this.rolesFromDB = roleService.getAllRoles();
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void createEmployee() {

        FacesContext context= FacesContext.getCurrentInstance();

        User user = makeUser();

        try {
            userService.createUser(user);

            FacesMessage message = new FacesMessage("User saved successfully", "Success");
            FacesContext.getCurrentInstance().addMessage(null, message);

            //clear the fields
//            this.role=null;
            this.roleName=null;
            this.firstName=null;
            this.lastName=null;
            this.email=null;
            this.password=null;
            this.username=null;

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            context.validationFailed();
        }
    }

    private User makeUser() {
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(roles);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setEmail(email);
        return user;
    }

    public int userCount(){
        int count=0;
        List<User> users = userService.getAllUsers();
        for(User user : users){
            count++;
        }
        return count;
    }
}
