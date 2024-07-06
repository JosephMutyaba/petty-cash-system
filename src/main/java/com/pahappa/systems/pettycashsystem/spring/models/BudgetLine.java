package com.pahappa.systems.pettycashsystem.spring.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class BudgetLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Date startDate;
    private Date endDate;
    private Double amount;
    private Double balance;
    private Date dateApproved;
    private String status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Requisition> requisitions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budgetLineCategory_id")
    private BudgetLineCategory budgetLineCategory;

    public BudgetLine() {}

    private BudgetLine(Long id, String description, Date startDate, Date endDate, Double amount, Double balance, Date dateApproved, String status, List<Requisition> requisitions, BudgetLineCategory budgetLineCategory) {
        this.id = id;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.balance = balance;
        this.dateApproved = dateApproved;
        this.status = status;
        this.requisitions = requisitions;
        this.budgetLineCategory = budgetLineCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Requisition> getRequisitions() {
        return requisitions;
    }

    public void setRequisitions(List<Requisition> requisitions) {
        this.requisitions = requisitions;
    }

    public BudgetLineCategory getBudgetLineCategory() {
        return budgetLineCategory;
    }

    public void setBudgetLineCategory(BudgetLineCategory budgetLineCategory) {
        this.budgetLineCategory = budgetLineCategory;
    }
}
