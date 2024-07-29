package com.pahappa.systems.pettycashsystem.spring.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class BudgetLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String review_comments;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private Double amount;


    private Double balance=amount; // initialised

    private Date dateApproved;

    @Column(nullable = false)
    private String status="Pending";  // DRAFT/ APPROVED

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "budgetline")
    private List<Requisition> requisitions;

    @ManyToOne
    private BudgetLineCategory budgetLineCategory;

    @Column(nullable = false)
    private Boolean deleted = false;

    public BudgetLine() {}

    private BudgetLine(Long id, Boolean deleted, String description, Date startDate, Date endDate, String review_comments, Double amount, Double balance, Date dateApproved, String status, List<Requisition> requisitions, BudgetLineCategory budgetLineCategory) {
        this.id = id;
        this.deleted=deleted;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.balance = balance;
        this.dateApproved = dateApproved;
        this.status = status;
        this.requisitions = requisitions;
        this.budgetLineCategory = budgetLineCategory;
        this.review_comments=review_comments;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public String getReview_comments() {
        return review_comments;
    }

    public void setReview_comments(String review_comments) {
        this.review_comments = review_comments;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetLine that = (BudgetLine) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getStartDate(), that.getStartDate()) && Objects.equals(getEndDate(), that.getEndDate()) && Objects.equals(getAmount(), that.getAmount()) && Objects.equals(getBalance(), that.getBalance()) && Objects.equals(getDateApproved(), that.getDateApproved()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getRequisitions(), that.getRequisitions()) && Objects.equals(getBudgetLineCategory(), that.getBudgetLineCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getStartDate(), getEndDate(), getAmount(), getBalance(), getDateApproved(), getStatus(), getRequisitions(), getBudgetLineCategory());
    }

    @Override
    public String toString() {
        return "BudgetLine:"+ description;
    }
}
