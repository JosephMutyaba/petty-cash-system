package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

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
    private List<Perm> permissionsPermissions;

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
        permissionsPermissions=new ArrayList<>();
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

    public void setViewPermissions(Set<Perm> viewPermissions) {

        if(viewPermissions != null && !viewPermissions.isEmpty()) {

            this.viewPermissions.add(permissionService.findByName(Perm.VIEW_PERMISSIONS));
            for (Perm perm: viewPermissions) {
                this.viewPermissions.add(permissionService.findByName(perm));
            }
        }
    }




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

    public List<Perm> getPermissionsPermissions() {
        initialiseSets();
        return permissionsPermissions;
    }

    public void setPermissionsPermissions(List<Perm> permissionsPermissions) {
        if(permissionsPermissions != null && !permissionsPermissions.isEmpty()) {
//            this.viewPermissions.add(permissionService.findByName(Perm.VIEW_PERMISSIONS));
            for (Perm perm: permissionsPermissions) {
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
        permissionsPermissions.clear();
        budgetLinePermissions.clear();
        rolePermissions.clear();
        requisitionPermissions.clear();
        employeePermissions.clear();


        for (Permission perm: allPermissions) {

            //EMPLOYEE PERMS
            if (perm.getName().equals(Perm.ADD_EMPLOYEE)) {
                this.employeePermissions.add(Perm.ADD_EMPLOYEE);
            }

            if (perm.getName().equals(Perm.DELETE_EMPLOYEE)) {
                this.employeePermissions.add(Perm.DELETE_EMPLOYEE);
            }

            if (perm.getName().equals(Perm.EDIT_EMPLOYEE)) {
                this.employeePermissions.add(Perm.EDIT_EMPLOYEE);
            }


            // ROLE PERMS
            if (perm.getName().equals(Perm.ADD_ROLE)) {
                this.rolePermissions.add(Perm.ADD_ROLE);
            }

            if (perm.getName().equals(Perm.DELETE_ROLE)) {
                this.rolePermissions.add(Perm.DELETE_ROLE);
            }

            if (perm.getName().equals(Perm.EDIT_ROLE)) {
                this.rolePermissions.add(Perm.EDIT_ROLE);
            }

            //REQUISITION PERMISSIONS
            if (perm.getName().equals(Perm.MAKE_REQUISITION)) {
                this.requisitionPermissions.add(Perm.MAKE_REQUISITION);
            }

            if (perm.getName().equals(Perm.CASHOUT_REQUISITION)) {
                this.requisitionPermissions.add(Perm.CASHOUT_REQUISITION);
            }

            if (perm.getName().equals(Perm.APPROVE_REQUISITION)) {
                this.requisitionPermissions.add(Perm.APPROVE_REQUISITION);
            }

            if (perm.getName().equals(Perm.REJECT_REQUISITION)) {
                this.requisitionPermissions.add(Perm.REJECT_REQUISITION);
            }

            if (perm.getName().equals(Perm.EDIT_REQUISITION)) {
                this.requisitionPermissions.add(Perm.EDIT_REQUISITION);
            }

            if (perm.getName().equals(Perm.DELETE_REQUISITION)) {
                this.requisitionPermissions.add(Perm.DELETE_REQUISITION);
            }



            //BUDGET LINE PERMISSIONS
            if (perm.getName().equals(Perm.MAKE_BUDGETLINE)) {
                this.budgetLinePermissions.add(Perm.MAKE_BUDGETLINE);
            }

            if (perm.getName().equals(Perm.APPROVE_BUDGETLINE)) {
                this.budgetLinePermissions.add(Perm.APPROVE_BUDGETLINE);
            }

            if (perm.getName().equals(Perm.REJECT_BUDGETLINE)) {
                this.budgetLinePermissions.add(Perm.REJECT_BUDGETLINE);
            }

            if (perm.getName().equals(Perm.EDIT_BUDGETLINE)) {
                this.budgetLinePermissions.add(Perm.EDIT_BUDGETLINE);
            }

            if (perm.getName().equals(Perm.DELETE_BUDGETLINE)) {
                this.budgetLinePermissions.add(Perm.DELETE_BUDGETLINE);
            }

            //PERMISSIONS PERMISSIONS
            if (perm.getName().equals(Perm.REQUEST_CHANGES)) {
                this.permissionsPermissions.add(Perm.REQUEST_CHANGES);
            }

            if (perm.getName().equals(Perm.VIEW_EMPLOYEES)) {
                this.employeePermissions.add(Perm.VIEW_EMPLOYEES);
            }

            if (perm.getName().equals(Perm.VIEW_ROLES)) {
                this.rolePermissions.add(Perm.VIEW_ROLES);
            }

            if (perm.getName().equals(Perm.VIEW_PERMISSIONS)) {
                this.permissionsPermissions.add(Perm.VIEW_PERMISSIONS);
            }

            if (perm.getName().equals(Perm.VIEW_REQUISITIONS)) {
                this.requisitionPermissions.add(Perm.VIEW_REQUISITIONS);
            }

            if (perm.getName().equals(Perm.VIEW_BUDGETLINES)) {
                this.budgetLinePermissions.add(Perm.VIEW_BUDGETLINES);
            }

            if (perm.getName().equals(Perm.VIEW_ACCOUNTABILITY)) {
                this.permissionsPermissions.add(Perm.VIEW_ACCOUNTABILITY);
            }

            if (perm.getName().equals(Perm.ACCEPT_REQUISITION)) {
                this.requisitionPermissions.add(Perm.ACCEPT_REQUISITION);
            }

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

        this.permissionsPermissions.clear();
        this.rolePermissions.clear();
        this.requisitionPermissions.clear();
        this.employeePermissions.clear();
        this.budgetLinePermissions.clear();
    }
}