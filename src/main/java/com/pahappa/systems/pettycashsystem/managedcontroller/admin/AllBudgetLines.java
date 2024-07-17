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
import java.util.stream.Collectors;

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

    private String searchTerm;

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

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
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
        filterBudgetLinesForActiveTab();
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

        filterBudgetLinesForActiveTab();
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




    public void filterBudgetLinesForActiveTab() {
        if (searchTerm == null || searchTerm.isEmpty()) {
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
        } else {
            budgetlinesForActiveTab = budgetlinesForActiveTab.stream()
                    .filter(budgetLine ->
                            budgetLine.getDescription().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(budgetLine.getBalance()).toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(budgetLine.getAmount()).toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(budgetLine.getStartDate()).toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(budgetLine.getEndDate()).toLowerCase().contains(searchTerm.toLowerCase()))
                    .collect(Collectors.toList());
        }
    }


}