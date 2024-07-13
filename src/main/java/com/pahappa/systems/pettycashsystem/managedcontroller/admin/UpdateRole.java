package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.Permission;
import com.pahappa.systems.pettycashsystem.spring.models.Role;
import com.pahappa.systems.pettycashsystem.spring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    private Set<Permission> viewEmployeesPermissions = new HashSet<>();
    private Set<Permission> viewRolesPermissions = new HashSet<>();
    private Set<Permission> viewRequisitionsPermissions = new HashSet<>();
    private Set<Permission> viewBudgetLinesPermissions = new HashSet<>();
    private Set<Permission> viewPermissions = new HashSet<>();

    private Role role;

    @PostConstruct
    public void init() {
        role = new Role();
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

    public Set<Permission> getViewEmployeesPermissions() {
        return viewEmployeesPermissions;
    }

    public void setViewEmployeesPermissions(Set<Permission> viewEmployeesPermissions) {
        this.viewEmployeesPermissions = viewEmployeesPermissions;
    }

    public Set<Permission> getViewRolesPermissions() {
        return viewRolesPermissions;
    }

    public void setViewRolesPermissions(Set<Permission> viewRolesPermissions) {
        this.viewRolesPermissions = viewRolesPermissions;
    }

    public Set<Permission> getViewRequisitionsPermissions() {
        return viewRequisitionsPermissions;
    }

    public void setViewRequisitionsPermissions(Set<Permission> viewRequisitionsPermissions) {
        this.viewRequisitionsPermissions = viewRequisitionsPermissions;
    }

    public Set<Permission> getViewBudgetLinesPermissions() {
        return viewBudgetLinesPermissions;
    }

    public void setViewBudgetLinesPermissions(Set<Permission> viewBudgetLinesPermissions) {
        this.viewBudgetLinesPermissions = viewBudgetLinesPermissions;
    }

    public Set<Permission> getViewPermissions() {
        return viewPermissions;
    }

    public void setViewPermissions(Set<Permission> viewPermissions) {
        this.viewPermissions = viewPermissions;
    }

    public void selectRole(Role selectedRole) {
        role = selectedRole;
        this.roleName = role.getName();
        this.roleDescription = role.getDescription();
        this.viewEmployeesPermissions = new HashSet<>(role.getPermissions()); // Adjust as needed
        this.viewRolesPermissions = new HashSet<>(role.getPermissions()); // Adjust as needed
        this.viewRequisitionsPermissions = new HashSet<>(role.getPermissions()); // Adjust as needed
        this.viewBudgetLinesPermissions = new HashSet<>(role.getPermissions()); // Adjust as needed
        this.viewPermissions = new HashSet<>(role.getPermissions()); // Adjust as needed
    }

    public void updateRole() {
        role.setName(roleName);
        role.setDescription(roleDescription);

        // Combine all permissions
        Set<Permission> allPermissions = new HashSet<>();
        allPermissions.addAll(viewEmployeesPermissions);
        allPermissions.addAll(viewRolesPermissions);
        allPermissions.addAll(viewRequisitionsPermissions);
        allPermissions.addAll(viewBudgetLinesPermissions);
        allPermissions.addAll(viewPermissions);

        role.setPermissions(allPermissions);

        roleService.updateRole(role);

        allRoles.init();

        this.role = null;
        this.roleName = null;
        this.roleDescription = null;
        this.viewEmployeesPermissions.clear();
        this.viewRolesPermissions.clear();
        this.viewRequisitionsPermissions.clear();
        this.viewBudgetLinesPermissions.clear();
        this.viewPermissions.clear();
    }
}












//package com.pahappa.systems.pettycashsystem.managedcontroller.admin;
//
//import com.pahappa.systems.pettycashsystem.spring.models.Role;
//import com.pahappa.systems.pettycashsystem.spring.services.RoleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.faces.view.ViewScoped;
//import javax.inject.Named;
//import java.io.Serializable;
//
//@Named
//@ViewScoped
//@Component
//public class UpdateRole implements Serializable {
//    @Autowired
//    private RoleService roleService;
//
//    @Autowired
//    private AllRoles allRoles;
//
//    private String roleName;
//    private String roleDescription;
//
//    private Role role;
//    @PostConstruct
//    public void init() {
//        role=new Role();
//    }
//
//    public String getRoleName() {
//        return roleName;
//    }
//
//    public void setRoleName(String roleName) {
//        this.roleName = roleName;
//    }
//
//    public String getRoleDescription() {
//        return roleDescription;
//    }
//
//    public void setRoleDescription(String roleDescription) {
//        this.roleDescription = roleDescription;
//    }
//
//    public void selectRole(Role selectedRole) {
//        role = selectedRole;
//        this.roleName = role.getName();
//        this.roleDescription = role.getDescription();
//
//    }
//
//    public void updateRole() {
//        role.setName(roleName);
//        role.setDescription(roleDescription);
//        roleService.updateRole(role);
//
//        allRoles.init();
//
//        this.role=null;
//        this.roleName=null;
//        this.roleDescription=null;
//
//    }
//}
