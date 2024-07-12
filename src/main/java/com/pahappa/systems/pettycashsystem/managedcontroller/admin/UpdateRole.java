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
public class UpdateRole implements Serializable {
    @Autowired
    private RoleService roleService;

    @Autowired
    private AllRoles allRoles;

    private String roleName;
    private String roleDescription;

    private Role role;
    @PostConstruct
    public void init() {
        role=new Role();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public void selectRole(Role selectedRole) {
        role = selectedRole;
        this.roleName = role.getName();
        this.roleDescription = role.getDescription();

    }

    public void updateRole() {
        role.setName(roleName);
        role.setDescription(roleDescription);
        roleService.updateRole(role);

        allRoles.init();

        this.role=null;
        this.roleName=null;
        this.roleDescription=null;

    }
}
