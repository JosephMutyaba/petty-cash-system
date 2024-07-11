package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.BudgetLineCategory;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineCategoryService;
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
public class AllBudgetLineCategories implements Serializable {
    @Autowired
    private BudgetLineCategoryService budgetLineCategoryService;

    private List<BudgetLineCategory> categories;
    @PostConstruct
    public void init() {
        categories = budgetLineCategoryService.getAllBudgetLineCategories();
    }

    public List<BudgetLineCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<BudgetLineCategory> categories) {
        this.categories = categories;
    }
}
