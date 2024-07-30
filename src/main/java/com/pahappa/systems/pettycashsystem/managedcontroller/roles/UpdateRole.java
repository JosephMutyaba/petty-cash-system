package com.pahappa.systems.pettycashsystem.managedcontroller.roles;

import com.pahappa.systems.pettycashsystem.spring.enums.Perm;
import com.pahappa.systems.pettycashsystem.spring.models.Permission;
import com.pahappa.systems.pettycashsystem.spring.models.Role;
import com.pahappa.systems.pettycashsystem.spring.services.PermissionService;
import com.pahappa.systems.pettycashsystem.spring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named
@RequestScoped
@Component
public class UpdateRole implements Serializable {
    @Autowired
    private RoleService roleService;

    @Autowired
    private AllRoles allRoles;

    @Autowired
    private PermissionService permissionService;

    private String roleName;
    private String roleDescription;

    //// list of permissions to display in the edit role dialog
    private List<Perm> budgetLinePermissions;
    private List<Perm> rolePermissions;
    private List<Perm> employeePermissions;
    private List<Perm> requisitionPermissions;
    private List<Perm> miscellaneousPermissions;

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

        viewEmployeesPermissions = new HashSet<Permission>();
        viewRolesPermissions = new HashSet<Permission>();
        viewRequisitionsPermissions = new HashSet<Permission>();
        viewBudgetLinesPermissions = new HashSet<Permission>();
        viewPermissions = new HashSet<Permission>();

        allPermissions = new HashSet<Permission>();

        budgetLinePermissions= new ArrayList<Perm>();
        rolePermissions=new ArrayList<>();
        employeePermissions=new ArrayList<>();
        requisitionPermissions=new ArrayList<>();
        miscellaneousPermissions =new ArrayList<>();
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

    public List<Perm> getRolePermissions() {
        initialiseSets();
        return rolePermissions;
    }

    public Set<Permission> getViewEmployeesPermissions() {
        return viewEmployeesPermissions;
    }

    public void setViewEmployeesPermissions(Set<Perm> viewEmployeesPermissions) {
        if (viewEmployeesPermissions != null && !viewEmployeesPermissions.isEmpty()) {
            this.viewEmployeesPermissions.add(permissionService.findByName(Perm.VIEW_EMPLOYEES));
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
            this.viewRolesPermissions.add(permissionService.findByName(Perm.VIEW_ROLES));
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
            this.viewRequisitionsPermissions.add(permissionService.findByName(Perm.VIEW_REQUISITIONS));
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
            this.viewBudgetLinesPermissions.add(permissionService.findByName(Perm.VIEW_BUDGETLINES));
            for (Perm perm: viewBudgetLinesPermissions) {
                this.viewBudgetLinesPermissions.add(permissionService.findByName(perm));
            }
        }

    }

    public Set<Permission> getViewPermissions() {
        initialiseSets();
        return viewPermissions;
    }

    /*public void setViewPermissions(Set<Perm> viewPermissions) {

        if(viewPermissions != null && !viewPermissions.isEmpty()) {

            this.viewPermissions.add(permissionService.findByName(Perm.VIEW_PERMISSIONS));
            for (Perm perm: viewPermissions) {
                this.viewPermissions.add(permissionService.findByName(perm));
            }
        }
    }*/

    public void setRolePermissions(List<Perm> rolePermissions) {
        if (rolePermissions != null && !rolePermissions.isEmpty()) {
//            this.viewRolesPermissions.add(permissionService.findByName(Perm.VIEW_ROLES));
            for (Perm perm: rolePermissions) {
                this.viewRolesPermissions.add(permissionService.findByName(perm));
            }
        }else {
            this.viewRolesPermissions =new HashSet<>();
        }
    }

    public List<Perm> getEmployeePermissions() {
        initialiseSets();
        return employeePermissions;
    }

    public void setEmployeePermissions(List<Perm> employeePermissions) {
        if (employeePermissions != null && !employeePermissions.isEmpty()) {
//            this.viewEmployeesPermissions.add(permissionService.findByName(Perm.VIEW_EMPLOYEES));
            for (Perm perm: employeePermissions) {
                this.viewEmployeesPermissions.add(permissionService.findByName(perm));
            }
        }else {
            this.viewEmployeesPermissions=new HashSet<>();
        }
    }

    public List<Perm> getRequisitionPermissions() {
        initialiseSets();
        return requisitionPermissions;
    }

    public void setRequisitionPermissions(List<Perm> requisitionPermissions) {
        if (requisitionPermissions != null && !requisitionPermissions.isEmpty()) {
//            this.viewRequisitionsPermissions.add(permissionService.findByName(Perm.VIEW_REQUISITIONS));
            for (Perm perm: requisitionPermissions) {
                this.viewRequisitionsPermissions.add(permissionService.findByName(perm));
            }
        }else {
            this.viewRequisitionsPermissions=new HashSet<>();
        }
    }

    public List<Perm> getMiscellaneousPermissions() {
        initialiseSets();
        return miscellaneousPermissions;
    }

    public void setMiscellaneousPermissions(List<Perm> miscellaneousPermissions) {
        if(miscellaneousPermissions != null && !miscellaneousPermissions.isEmpty()) {
//            this.viewPermissions.add(permissionService.findByName(Perm.VIEW_PERMISSIONS));
            for (Perm perm: miscellaneousPermissions) {
                this.viewPermissions.add(permissionService.findByName(perm));
            }
        }else {
            this.viewPermissions=new HashSet<>();
        }
    }

    public List<Perm> getBudgetLinePermissions() {
        initialiseSets();
        return budgetLinePermissions;
    }

    public void setBudgetLinePermissions(List<Perm> budgetLinePermissions) {
        if (budgetLinePermissions != null && !budgetLinePermissions.isEmpty()) {
//            this.viewBudgetLinesPermissions.add(permissionService.findByName(Perm.VIEW_BUDGETLINES));
            for (Perm perm: budgetLinePermissions) {
                this.viewBudgetLinesPermissions.add(permissionService.findByName(perm));
            }
        }else{
            this.viewBudgetLinesPermissions=new HashSet<>();
        }
    }

    public void selectRole(Role selectedRole) {
        role = selectedRole;
        this.roleName = role.getName();
        this.roleDescription = role.getDescription();

        this.allPermissions=role.getPermissions();

        initialiseSets();
    }

    private void initialiseSets(){
        miscellaneousPermissions.clear();
        budgetLinePermissions.clear();
        rolePermissions.clear();
        requisitionPermissions.clear();
        employeePermissions.clear();


        for (Permission permission: allPermissions) {
            Perm perm = permission.getName();
            String name = perm.toString();
            if (name.contains("EMPLOYEE"))
                employeePermissions.add(perm);
            else if (name.contains("ROLE"))
                rolePermissions.add(perm);
            else if (name.contains("REQUISITION"))
                requisitionPermissions.add(perm);
            else if (name.contains("BUDGETLINE"))
                budgetLinePermissions.add(perm);
            else
                miscellaneousPermissions.add(perm);
        }
    }


    public void updateRole() {
        FacesContext context = FacesContext.getCurrentInstance();

        role.setName(roleName);
        role.setDescription(roleDescription);

        role.getPermissions().clear();


        // Combine all permissions
        allPermissions.addAll(viewEmployeesPermissions);
        allPermissions.addAll(viewRolesPermissions);
        allPermissions.addAll(viewRequisitionsPermissions);
        allPermissions.addAll(viewBudgetLinesPermissions);
        allPermissions.addAll(viewPermissions);

        role.setPermissions(allPermissions);

        try {
            roleService.updateRole(role);

            clearVals();

            FacesMessage message = new FacesMessage("Role updated successfully", "Success");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",e.getMessage()));
            context.validationFailed();
        }

    }

    public void clearVals(){
        allRoles.init();

        this.role = new Role();
        this.roleName = null;
        this.roleDescription = null;
        this.viewEmployeesPermissions.clear();
        this.viewRolesPermissions.clear();
        this.viewRequisitionsPermissions.clear();
        this.viewBudgetLinesPermissions.clear();
        this.viewPermissions.clear();

        this.miscellaneousPermissions.clear();
        this.rolePermissions.clear();
        this.requisitionPermissions.clear();
        this.employeePermissions.clear();
        this.budgetLinePermissions.clear();
    }
}