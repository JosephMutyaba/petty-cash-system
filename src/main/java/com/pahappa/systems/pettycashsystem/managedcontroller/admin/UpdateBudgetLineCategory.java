package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.BudgetLineCategory;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@Component
public class UpdateBudgetLineCategory implements Serializable {
    @Autowired
    private BudgetLineCategoryService budgetLineCategoryService;

    @Autowired
    private AllBudgetLineCategories allCategories;

    private BudgetLineCategory budgetLineCategory;
    private String cat_name;

    @PostConstruct
    public void init() {
        budgetLineCategory = new BudgetLineCategory();
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public void selectCategory(BudgetLineCategory selectedCategory){
        this.budgetLineCategory = selectedCategory;
        this.cat_name = budgetLineCategory.getName();
    }

    public void updateCategory(){
        budgetLineCategory.setName(cat_name);
        budgetLineCategoryService.updateBudgetLineCategory(budgetLineCategory);
        allCategories.init();

        cat_name = null;
    }

}
