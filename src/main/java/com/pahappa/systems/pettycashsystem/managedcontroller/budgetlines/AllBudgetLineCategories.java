package com.pahappa.systems.pettycashsystem.managedcontroller.budgetlines;

import com.pahappa.systems.pettycashsystem.spring.models.BudgetLineCategory;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineCategoryService;
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
public class AllBudgetLineCategories implements Serializable {
    @Autowired
    private BudgetLineCategoryService budgetLineCategoryService;

    private List<BudgetLineCategory> categories;

    private String searchTerm;

    @PostConstruct
    public void init() {
        categories = budgetLineCategoryService.getAllBudgetLineCategories();
    }

    public List<BudgetLineCategory> getCategories() {
        categories = budgetLineCategoryService.getAllBudgetLineCategories();
        filterCategories();
        return categories;
    }

    public void setCategories(List<BudgetLineCategory> categories) {
        this.categories = budgetLineCategoryService.getAllBudgetLineCategories();
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void filterCategories() {
        if (searchTerm == null || searchTerm.isEmpty()) {
            categories=budgetLineCategoryService.getAllBudgetLineCategories();
        } else {
            categories = categories.stream()
                    .filter(category ->
                            category.getName().toLowerCase().contains(searchTerm.toLowerCase())
                    ).collect(Collectors.toList());
        }
    }
}
