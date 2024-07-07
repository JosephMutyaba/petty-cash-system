package com.pahappa.systems.pettycashsystem.spring.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class BudgetLineCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BudgetLine> budgetLines;

    public BudgetLineCategory() {
    }

    private BudgetLineCategory(Long id, String name, List<BudgetLine> budgetLines) {
        this.id = id;
        this.name = name;
        this.budgetLines = budgetLines;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BudgetLine> getBudgetLines() {
        return budgetLines;
    }

    public void setBudgetLines(List<BudgetLine> budgetLines) {
        this.budgetLines = budgetLines;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetLineCategory that = (BudgetLineCategory) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getBudgetLines(), that.getBudgetLines());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBudgetLines());
    }


    @Override
    public String toString() {
        return "BudgetLineCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budgetLines=" + budgetLines +
                '}';
    }
}
