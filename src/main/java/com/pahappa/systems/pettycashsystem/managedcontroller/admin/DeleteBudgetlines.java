package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineService;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
@Component
public class DeleteBudgetlines implements Serializable {
    @Autowired
    private BudgetLineService budgetLineService;

    private BudgetLine budgetLine;

    @Autowired
    private AllBudgetLines allBudgetLines;

    public void deleteSpecificBudgetLine() {
        budgetLineService.deleteBudgetLine(budgetLine.getId());
        allBudgetLines.update();
    }

    public void selectSpecificBudgetLine(BudgetLine budgetLine) {
        this.budgetLine = budgetLine;
    }

    public void deleteAllBudgetLinesOfStatus() {
        String tabId = allBudgetLines.getTabId();
        if (tabId == null) {
            System.err.println("Error: tabId is null");
            return;
        }

        switch (tabId) {
            case "pendingTab":
                System.out.println("\n\nPending deletion status......................\n\n");

                budgetLineService.deleteBudgetLinesByStatus("Pending");
                allBudgetLines.update();
                break;
            case "approvedTab":
                System.out.println("\n\nApproved deletion status......................\n\n");
                budgetLineService.deleteBudgetLinesByStatus("Approved");
                allBudgetLines.update();
                break;
            case "rejectedTab":
                budgetLineService.deleteBudgetLinesByStatus("Rejected");
                allBudgetLines.update();
                break;
            case "editLineTab":
                budgetLineService.deleteBudgetLinesByStatus("RequestEdit");
                allBudgetLines.update();
                break;
            default:
                System.err.println("Error: Unknown tabId - " + tabId);
                break;
        }
    }
}


















//
//@Named
//@ViewScoped
//@Component
//public class DeleteBudgetlines implements Serializable {
//    @Autowired
//    private BudgetLineService budgetLineService;
//
//    private BudgetLine budgetLine;
//
//    @Autowired
//    private AllBudgetLines allBudgetLines;
//
//
//    public void deleteSpecificBudgetLine() {
//        budgetLineService.deleteBudgetLine(budgetLine.getId());
//        allBudgetLines.update();
//    }
//
//    public void selectSpecificBudgetLine(BudgetLine budgetLine) {
//        this.budgetLine = budgetLine;
//    }
//
//    public void deleteAllBudgetLinesOfStatus() {
//        String tabId="";
//        tabId = allBudgetLines.getTabId();
//        switch (tabId) {
//            case "pendingTab":
//                budgetLineService.deleteBudgetLinesByStatus("Pending");
//                allBudgetLines.update();
//                break;
//            case "approvedTab":
//                budgetLineService.deleteBudgetLinesByStatus("Approved");
//
//                break;
//            case "rejectedTab":
//                budgetLineService.deleteBudgetLinesByStatus("Rejected");
//                break;
//            case "editLineTab":
//                budgetLineService.deleteBudgetLinesByStatus("RequestEdit");
//                break;
//        }
//    }
//}
