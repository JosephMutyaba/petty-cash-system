package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineService;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Component
public class AllBudgetLines implements Serializable {
    @Autowired
    private BudgetLineService budgetLineService;

    private List<BudgetLine> pendingBudgetLines;
    private List<BudgetLine> approvedBudgetLines;
    private List<BudgetLine> rejectedBudgetLines;
    private List<BudgetLine> editRequestBudgetLines;
    private List<BudgetLine> expiredBudgetLines;

    private String tabId;

    @PostConstruct
    public void init() {
        editRequestBudgetLines = budgetLineService.getAllBudgetlinesByStatus("RequestEdit");
        pendingBudgetLines = budgetLineService.getAllBudgetlinesByStatus("Pending");
        rejectedBudgetLines = budgetLineService.getAllBudgetlinesByStatusRejected();
        approvedBudgetLines = budgetLineService.getAllBudgetlinesByStatus("Approved");
        expiredBudgetLines = budgetLineService.getAllExpiredBudgetLines();
        tabId = "pendingTab";
    }

    public List<BudgetLine> getPendingBudgetLines() {
        return budgetLineService.getAllBudgetlinesByStatus("Pending");
    }

    public void setPendingBudgetLines(List<BudgetLine> pendingRequisitions) {
        this.pendingBudgetLines = pendingRequisitions;
    }

    public List<BudgetLine> getApprovedBudgetLines() {
        return budgetLineService.getAllBudgetlinesByStatus("Approved");
    }

    public void setApprovedBudgetLines(List<BudgetLine> approvedBudgetLines) {
        this.approvedBudgetLines = approvedBudgetLines;
    }

    public List<BudgetLine> getRejectedBudgetLines() {
        return budgetLineService.getAllBudgetlinesByStatusRejected();
    }

    public void setRejectedBudgetLines(List<BudgetLine> rejectedBudgetLines) {
        this.rejectedBudgetLines = budgetLineService.getAllBudgetlinesByStatusRejected();;
    }

    public List<BudgetLine> getEditRequestBudgetLines() {
        return budgetLineService.getAllBudgetlinesByStatus("RequestEdit");
    }

    public void setEditRequestBudgetLines(List<BudgetLine> editRequestBudgetLines) {
        this.editRequestBudgetLines = budgetLineService.getAllBudgetlinesByStatus("RequestEdit");
    }

    public List<BudgetLine> getExpiredBudgetLines() {
        return budgetLineService.getAllExpiredBudgetLines();
    }

    public void setExpiredBudgetLines(List<BudgetLine> expiredBudgetLines) {
        this.expiredBudgetLines = budgetLineService.getAllExpiredBudgetLines();
    }

    private int activeTab = 0;
    private List<BudgetLine> budgetlinesForActiveTab;


    public void onTabChange(TabChangeEvent event) {
        tabId = event.getTab().getId();
        switch (tabId) {
            case "pendingTab":
                activeTab = 0;
                budgetlinesForActiveTab = getPendingBudgetLines();
                break;
            case "approvedTab":
                activeTab = 1;
                budgetlinesForActiveTab = getApprovedBudgetLines();
                break;
            case "rejectedTab":
                activeTab = 2;
                budgetlinesForActiveTab = getRejectedBudgetLines();
                break;
            case "editLineTab":
                activeTab = 3;
                budgetlinesForActiveTab = getEditRequestBudgetLines();
                break;
            case "expiredTab":
                activeTab = 4;
                budgetlinesForActiveTab = getExpiredBudgetLines();
                break;
            default:
                System.err.println("Error: Unknown tabId - " + tabId);
                break;
        }
    }

    public void update() {
        switch (activeTab) {
            case 0:
                budgetlinesForActiveTab = getPendingBudgetLines();
                break;
            case 1:
                budgetlinesForActiveTab = getApprovedBudgetLines();
                break;
            case 2:
                budgetlinesForActiveTab = getRejectedBudgetLines();
                break;
            case 3:
                budgetlinesForActiveTab = getEditRequestBudgetLines();
                break;
            case 4:
                budgetlinesForActiveTab = getExpiredBudgetLines();
                break;
            default:
                System.err.println("Error: Unknown activeTab - " + activeTab);
                break;
        }
    }

    public List<BudgetLine> getBudgetLinesForActiveTab() {
        if (budgetlinesForActiveTab == null) {
            budgetlinesForActiveTab = getPendingBudgetLines();
        }
        return budgetlinesForActiveTab;
    }

    public int getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(int activeTab) {
        this.activeTab = activeTab;
    }

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }
}




























//@Named
//@ViewScoped
//@Component
//public class AllBudgetLines implements Serializable {
//    @Autowired
//    private BudgetLineService budgetLineService;
//
//    private List<BudgetLine> pendingBudgetLines;
//    private List<BudgetLine> approvedBudgetLines;
//    private List<BudgetLine> rejectedBudgetLines;
//    private List<BudgetLine> paidBudgetLines;
//
//    public String tabId;
//
//
//
//    @PostConstruct
//    public void init() {
//        paidBudgetLines=budgetLineService.getAllBudgetlinesByStatus("editLineTab");
//        pendingBudgetLines=budgetLineService.getAllBudgetlinesByStatus("Pending");
//        rejectedBudgetLines=budgetLineService.getAllBudgetlinesByStatus("Rejected");
//        approvedBudgetLines=budgetLineService.getAllBudgetlinesByStatus("Approved");
//    }
//
//    public List<BudgetLine> getPendingBudgetLines() {
//        return budgetLineService.getAllBudgetlinesByStatus("Pending");
//    }
//
//    public void setPendingBudgetLines(List<BudgetLine> pendingRequisitions) {
//        this.pendingBudgetLines = pendingRequisitions;
//    }
//
//    public List<BudgetLine> getApprovedBudgetLines() {
//        return budgetLineService.getAllBudgetlinesByStatus("Approved");
//    }
//
//    public void setApprovedBudgetLines(List<BudgetLine> approvedBudgetLines) {
//        this.approvedBudgetLines = approvedBudgetLines;
//    }
//
//    public List<BudgetLine> getRejectedBudgetLines() {
//        return budgetLineService.getAllBudgetlinesByStatus("Rejected");
//    }
//
//    public void setRejectedBudgetLines(List<BudgetLine> rejectedBudgetLines) {
//        this.rejectedBudgetLines = rejectedBudgetLines;
//    }
//
//    public List<BudgetLine> getPaidBudgetLines() {
//        return budgetLineService.getAllBudgetlinesByStatus("EditRequest");
//    }
//
//    public void setPaidBudgetLines(List<BudgetLine> paidBudgetLines) {
//        this.paidBudgetLines = paidBudgetLines;
//    }
//
//
//
//
//
//
//
//
//
//
//
//    private int activeTab = 0;
//    private List<BudgetLine> budgetlinesForActiveTab;
//
//
//    public void onTabChange(TabChangeEvent event) {
//        tabId = event.getTab().getId();
//        switch (tabId) {
//            case "pendingTab":
//                activeTab = 0;
//                budgetlinesForActiveTab = getPendingBudgetLines();
//                break;
//            case "approvedTab":
//                activeTab = 1;
//                budgetlinesForActiveTab = getApprovedBudgetLines();
//                break;
//            case "rejectedTab":
//                activeTab = 2;
//                budgetlinesForActiveTab = getRejectedBudgetLines();
//                break;
//            case "editLineTab":
//                activeTab = 3;
//                budgetlinesForActiveTab = getPaidBudgetLines();
//                break;
//        }
//    }
//    public void update() {
//        budgetlinesForActiveTab = getPendingBudgetLines();
//    }
//
//    public List<BudgetLine> getBudgetLinesForActiveTab() {
//        if (budgetlinesForActiveTab == null) {
//            budgetlinesForActiveTab = getPendingBudgetLines();
//        }
//        return budgetlinesForActiveTab;
//    }
//
//    public int getActiveTab() {
//        return activeTab;
//    }
//
//    public void setActiveTab(int activeTab) {
//        this.activeTab = activeTab;
//    }
//}
