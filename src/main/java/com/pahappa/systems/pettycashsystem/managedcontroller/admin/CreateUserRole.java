package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.exceptions.MinimumLengthException;
import com.pahappa.systems.pettycashsystem.exceptions.NameExistsException;
import com.pahappa.systems.pettycashsystem.exceptions.NullFieldException;
import com.pahappa.systems.pettycashsystem.spring.enums.Perm;
import com.pahappa.systems.pettycashsystem.spring.models.Permission;
import com.pahappa.systems.pettycashsystem.spring.models.Role;
import com.pahappa.systems.pettycashsystem.spring.services.PermissionService;
import com.pahappa.systems.pettycashsystem.spring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Named
@ViewScoped
@Component
public class CreateUserRole implements Serializable {
    @Autowired
    private RoleService roleService;

    @Autowired
    private AllRoles allRoles;

    @Autowired
    private PermissionService permissionService;

    private String roleName;
    private String roleDescription;


    Set<Permission> allPermissions;


    private Set<Permission> viewEmployeesPermissions;
    private Set<Permission> viewRolesPermissions;
    private Set<Permission> viewRequisitionsPermissions;
    private Set<Permission> viewBudgetLinesPermissions;
    private Set<Permission> viewPermissions;

    private Role role;

    @PostConstruct
    public void init() {
        role = new Role();

        viewEmployeesPermissions = new HashSet<>();
        viewRolesPermissions = new HashSet<>();
        viewRequisitionsPermissions = new HashSet<>();
        viewBudgetLinesPermissions = new HashSet<>();
        viewPermissions = new HashSet<>();

        allPermissions = new HashSet<>();
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

    public void setViewEmployeesPermissions(Set<Perm> viewEmployeesPermissions) {
        if (viewEmployeesPermissions != null && !viewEmployeesPermissions.isEmpty()) {
//            this.viewEmployeesPermissions.add(permissionService.findByName(Perm.VIEW_EMPLOYEES));
            for (Perm perm: viewEmployeesPermissions) {
                this.viewEmployeesPermissions.add(permissionService.findByName(perm));
            }
        }
    }

    public Set<Permission> getViewRolesPermissions() {
        return viewRolesPermissions;
    }

    public void setViewRolesPermissions(Set<Perm> viewRolesPermissions) {
        if (viewRolesPermissions != null && !viewRolesPermissions.isEmpty()) {
//            this.viewRolesPermissions.add(permissionService.findByName(Perm.VIEW_ROLES));
            for (Perm perm: viewRolesPermissions) {
                this.viewRolesPermissions.add(permissionService.findByName(perm));
            }
        }

    }

    public Set<Permission> getViewRequisitionsPermissions() {
        return viewRequisitionsPermissions;
    }

    public void setViewRequisitionsPermissions(Set<Perm> viewRequisitionsPermissions) {
        if (viewRequisitionsPermissions != null && !viewRequisitionsPermissions.isEmpty()) {
//            this.viewRequisitionsPermissions.add(permissionService.findByName(Perm.VIEW_REQUISITIONS));
            for (Perm perm: viewRequisitionsPermissions) {
                this.viewRequisitionsPermissions.add(permissionService.findByName(perm));
            }
        }

    }

    public Set<Permission> getViewBudgetLinesPermissions() {
        return viewBudgetLinesPermissions;
    }

    public void setViewBudgetLinesPermissions(Set<Perm> viewBudgetLinesPermissions) {
        if (viewBudgetLinesPermissions != null && !viewBudgetLinesPermissions.isEmpty()) {
//            this.viewBudgetLinesPermissions.add(permissionService.findByName(Perm.VIEW_BUDGETLINES));
            for (Perm perm: viewBudgetLinesPermissions) {
                this.viewBudgetLinesPermissions.add(permissionService.findByName(perm));
            }
        }

    }

    public Set<Permission> getViewPermissions() {
        return viewPermissions;
    }

    public void setViewPermissions(Set<Perm> viewPermissions) {

        if(viewPermissions != null && !viewPermissions.isEmpty()) {
//            this.viewPermissions.add(permissionService.findByName(Perm.VIEW_PERMISSIONS));
            for (Perm perm: viewPermissions) {
                this.viewPermissions.add(permissionService.findByName(perm));
            }
        }
    }

    public void createRole() {
        role.setName(roleName);
        role.setDescription(roleDescription);

        // Combine all permissions
        allPermissions.addAll(viewEmployeesPermissions);
        allPermissions.addAll(viewRolesPermissions);
        allPermissions.addAll(viewRequisitionsPermissions);
        allPermissions.addAll(viewBudgetLinesPermissions);
        allPermissions.addAll(viewPermissions);

        role.setPermissions(allPermissions);

        try {
            roleService.createRole(role);
            allRoles.init();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }finally {
            this.role = new Role();
            this.roleName = null;
            this.roleDescription = null;
            this.viewEmployeesPermissions.clear();
            this.viewRolesPermissions.clear();
            this.viewRequisitionsPermissions.clear();
            this.viewBudgetLinesPermissions.clear();
            this.viewPermissions.clear();
        }



    }
}