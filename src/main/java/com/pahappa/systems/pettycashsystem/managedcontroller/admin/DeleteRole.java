package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.Role;
import com.pahappa.systems.pettycashsystem.spring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@Component
public class DeleteRole implements Serializable {
    @Autowired
    private RoleService roleService;

    @Autowired
    private AllRoles allRoles;

    private Role role;

    @PostConstruct
    public void init() {
        role = new Role();
    }

    public void selectRole(Role selectedRole){
        this.role = selectedRole;
    }

    public void deleteARole(){
        roleService.deleteRole(role.getId());
        allRoles.init();
    }

    public void deleteAllRoles(){
        roleService.deleteAllRoles();
        allRoles.init();
    }
}
