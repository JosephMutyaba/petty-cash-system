package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.exceptions.NameExistsException;
import com.pahappa.systems.pettycashsystem.exceptions.NullFieldException;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLineCategory;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class CreateBudgetLineCategory implements Serializable {

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

    public void createCategory(){
        budgetLineCategory.setName(cat_name);
        try {
            budgetLineCategoryService.createBudgetLineCategory(budgetLineCategory);
            allCategories.init();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", e.getMessage()));
        }

    }
}
