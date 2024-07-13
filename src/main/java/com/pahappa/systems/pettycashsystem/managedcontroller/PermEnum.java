package com.pahappa.systems.pettycashsystem.managedcontroller;

import com.pahappa.systems.pettycashsystem.spring.enums.Perm;
import com.pahappa.systems.pettycashsystem.spring.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@SessionScoped
public class PermEnum implements Serializable {

    @Autowired
    private PermissionService permissionService;

    public List<Perm> getEmployeePermissions() {
        return Arrays.asList(Perm.ADD_EMPLOYEE, Perm.DELETE_EMPLOYEE, Perm.EDIT_EMPLOYEE);
    }

    public List<Perm> getRolePermissions() {
        return Arrays.asList(Perm.ADD_ROLE, Perm.DELETE_ROLE, Perm.EDIT_ROLE);
    }

    public List<Perm> getRequisitionPermissions() {
        return Arrays.asList(Perm.MAKE_REQUISITION, Perm.APPROVE_REQUISITION, Perm.REJECT_REQUISITION, Perm.EDIT_REQUISITION, Perm.DELETE_REQUISITION);
    }

    public List<Perm> getBudgetLinePermissions() {
        return Arrays.asList(Perm.MAKE_BUDGETLINE, Perm.APPROVE_BUDGETLINE, Perm.REJECT_BUDGETLINE, Perm.EDIT_BUDGETLINE, Perm.DELETE_BUDGETLINE);
    }

    public List<Perm> getPermissions() {
        return Arrays.asList(Perm.REQUEST_CHANGES, Perm.VIEW_EMPLOYEES, Perm.VIEW_ROLES, Perm.VIEW_REQUISITIONS, Perm.VIEW_BUDGETLINES, Perm.VIEW_PERMISSIONS);
    }
}
