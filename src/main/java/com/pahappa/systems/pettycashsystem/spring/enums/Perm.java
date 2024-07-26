package com.pahappa.systems.pettycashsystem.spring.enums;

public enum Perm {

    ADD_EMPLOYEE("Add Employee"),
    DELETE_EMPLOYEE("Delete Employee"),
    EDIT_EMPLOYEE("Edit Employee"),

    ADD_ROLE("Add Role"),
    DELETE_ROLE("Delete Role"),
    EDIT_ROLE("Edit Role"),

    MAKE_REQUISITION("Make Requisition"),
    APPROVE_REQUISITION("Approve Requisition"),
    REJECT_REQUISITION("Reject Requisition"),
    EDIT_REQUISITION("Edit Requisition"),
    DELETE_REQUISITION("Delete Requisition"),
    CASHOUT_REQUISITION("Cash out Requisition"),

    MAKE_BUDGETLINE("Make BudgetLine"),
    APPROVE_BUDGETLINE("Approve BudgetLine"),
    REJECT_BUDGETLINE("Reject BudgetLine"),
    EDIT_BUDGETLINE("Edit BudgetLine"),
    DELETE_BUDGETLINE("Delete BudgetLine"),


    VIEW_EMPLOYEES("View Employees"),
    VIEW_ROLES("View Roles"),
    VIEW_REQUISITIONS("View Requisitions"),
    VIEW_BUDGETLINES("View BudgetLines"),

    REQUEST_CHANGES("Request Changes"),

    ACCEPT_REQUISITION("Accept Requisition"),
    VIEW_ACCOUNTABILITY("View Accountability"),
    VIEW_DASHBOARD("View Dashboard"),;

    private final String definition;

    Perm(String definition) {
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }
}




