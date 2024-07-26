package com.pahappa.systems.pettycashsystem.managedcontroller;

import com.pahappa.systems.pettycashsystem.spring.enums.Perm;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@SessionScoped
public class PermEnum implements Serializable {

    @PostConstruct
    void ini() {
        
    }

    public List<Perm> getEmployeePermissions() {
        return Arrays.asList(Perm.VIEW_EMPLOYEES,Perm.ADD_EMPLOYEE, Perm.DELETE_EMPLOYEE, Perm.EDIT_EMPLOYEE);
    }

    public List<Perm> getRolePermissions() {
        return Arrays.asList(Perm.VIEW_ROLES,Perm.ADD_ROLE, Perm.DELETE_ROLE, Perm.EDIT_ROLE);
    }

    public List<Perm> getRequisitionPermissions() {
        return Arrays.asList(Perm.VIEW_REQUISITIONS,Perm.MAKE_REQUISITION, Perm.APPROVE_REQUISITION, Perm.ACCEPT_REQUISITION, Perm.REJECT_REQUISITION, Perm.EDIT_REQUISITION, Perm.DELETE_REQUISITION,Perm.CASHOUT_REQUISITION);
    }

    public List<Perm> getBudgetLinePermissions() {
        return Arrays.asList(Perm.VIEW_BUDGETLINES,Perm.MAKE_BUDGETLINE, Perm.APPROVE_BUDGETLINE, Perm.REJECT_BUDGETLINE, Perm.EDIT_BUDGETLINE, Perm.DELETE_BUDGETLINE);
    }

    public List<Perm> getPermissions() {
        return Arrays.asList(Perm.REQUEST_CHANGES,Perm.VIEW_ACCOUNTABILITY,Perm.VIEW_DASHBOARD);
    }
}
