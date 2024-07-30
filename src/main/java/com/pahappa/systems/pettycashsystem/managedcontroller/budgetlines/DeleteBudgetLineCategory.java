package com.pahappa.systems.pettycashsystem.managedcontroller.budgetlines;

import com.pahappa.systems.pettycashsystem.spring.models.BudgetLineCategory;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@Component
public class DeleteBudgetLineCategory implements Serializable {
    @Autowired
    BudgetLineCategoryService budgetLineCategoryService;

    @Autowired
    private AllBudgetLineCategories allCategories;

    private BudgetLineCategory budgetLineCategory;

    @PostConstruct
    public void init() {
        budgetLineCategory = new BudgetLineCategory();
    }

    public void selectBudgetLineCategory(BudgetLineCategory selectedBudgetLineCategory) {
        this.budgetLineCategory = selectedBudgetLineCategory;

        FacesMessage message = new FacesMessage("Deleted Successfully", "Deleted Successfully");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void deleteBudgetLineCategory() {
        budgetLineCategoryService.deleteBudgetLineCategory(budgetLineCategory.getId());
        allCategories.init();
    }

    public void deleteAllBudgetLineCategories() {
        budgetLineCategoryService.deleteAllCategories();
        allCategories.init();
    }

}
