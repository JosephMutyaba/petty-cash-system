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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named
@ViewScoped
@Component
public class UpdateEmployee implements Serializable {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    private String firstName;
    private String lastName;
    private String email;
    private String password;
//    private Role role;
    private Set<Role> userRoles;
    private List<String> roleNames;
    private List<String> rolesFromDatabase;
    private String username;
    private User user;

    @PostConstruct
    public void init() {
        setRolesFromDatabase();
        userRoles = new HashSet<>();
        roleNames = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRolesFromDatabase() {
        setRolesFromDatabase();
        return rolesFromDatabase;
    }

    public void setRolesFromDatabase() {
        rolesFromDatabase = roleService.getAllRoles().stream()
                .map(Role::getName).collect(Collectors.toList());
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

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public void updateEmployee() {
        FacesContext context = FacesContext.getCurrentInstance();


        user.setUsername(username);
        user.setPassword(password);

        for (String roleName:roleNames)
            userRoles.add(roleService.findByName(roleName));

        user.setRoles(userRoles);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setEmail(email);

        try {
            userService.updateUser(user);

            //clear the fields
//            this.role=null;
            clearVariables();

            FacesMessage message = new FacesMessage("User updated successfully", "Success");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),e.getMessage()));
            context.validationFailed();
        }
    }

    private void clearVariables() {
        userRoles.clear();
        roleNames.clear();
        firstName=null;
        lastName=null;
        email=null;
        password=null;
        username=null;
    }

    public void selectedUser(User userSelected){
        clearVariables();
        this.user=userSelected;
        for (Role r:user.getRoles())
            roleNames.add(r.getName());
        this.firstName=user.getFirstname();
        this.lastName=user.getLastname();
        this.email=user.getEmail();
        this.password=user.getPassword();
        this.username=userSelected.getUsername();
    }
}
