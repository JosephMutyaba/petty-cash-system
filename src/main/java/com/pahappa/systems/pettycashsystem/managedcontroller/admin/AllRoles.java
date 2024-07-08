package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.Role;
import com.pahappa.systems.pettycashsystem.spring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Component
public class AllRoles implements Serializable {
    @Autowired
    private RoleService roleService;

    private List<Role> roles;


    @PostConstruct
    public void init() {
        roles = roleService.getAllRoles();
    }

    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
