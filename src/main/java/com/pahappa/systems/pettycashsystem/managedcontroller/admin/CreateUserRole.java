package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.enums.Permission;
import com.pahappa.systems.pettycashsystem.spring.models.Role;
import com.pahappa.systems.pettycashsystem.spring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.EnumSet;
import java.util.Set;

@Named
@ViewScoped
@Component
public class CreateUserRole implements Serializable {
    @Autowired
    private RoleService roleService;

    @Autowired
    private AllRoles allRoles;

    private String roleName;
    private String roleDescription;
    private Set<Permission> rolePermissions = EnumSet.noneOf(Permission.class);

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

    public Set<Permission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<Permission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public void createRole() {
        role.setName(roleName);
        role.setDescription(roleDescription);
        role.setPermissions(rolePermissions);
        roleService.createRole(role);

        allRoles.init();

        this.role=new Role();
        this.roleName=null;
        this.roleDescription=null;

    }

}
