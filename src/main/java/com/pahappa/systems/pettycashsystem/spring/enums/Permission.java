package com.pahappa.systems.pettycashsystem.spring.enums;

public enum Permission {


    add_employee("Add Employee"),
    delete_employee("Delete Employee"),
    edit_employee("Edit Employee"),

    add_role("Add Role"),
    delete_role("Delete Role"),
    edit_role("Edit Role"),

    make_requisition("Make Requisition"),
    approve_requisition("Approve Requisition"),
    reject_requisition("Reject Requisition"),
    edit_requisition("Edit Requisition"),
    delete_requisition("Delete Requisition"),

    make_budgetLine("Make BudgetLine"),
    approve_budgetLine("Approve BudgetLine"),
    reject_budgetLine("Reject BudgetLine"),
    edit_budgetLine("Edit BudgetLine"),
    delete_budgetLine("Delete BudgetLine"),

    view_employees("View Employees"),
    view_roles("View Roles"),
    view_requisitions("View Requisitions"),
    view_budgetLines("View BudgetLines"),
    view_permissions("View Permissions"),

    request_changes("Request Changes");

    public final String definition;
    public String getDefinition() {
        return definition;
    }

    Permission(String definition) {
        this.definition = definition;
    }

//    boolean hasPermission(String... perms) {
//    x= CurrentUser.permissions
//    for permission in x:
//      for item in perms:
//          if x.tostring.equals(item) return true;
//    return false;
//    }
}
