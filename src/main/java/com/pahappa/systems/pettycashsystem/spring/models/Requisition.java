package com.pahappa.systems.pettycashsystem.spring.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Requisition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String justification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Accountability accountability;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budgetline_id")
    private BudgetLine budgetline;

    private String ops_man_review;
    private String ceo_review;

    private Long estimatedAmount;
    private Long amountGranted;
    private String status;
    private Date dateCreated;
    private Date maxDateNeeded;
    private Date dateApproved;

    public Requisition() {
    }

    private Requisition(Long id, String justification, User user, Accountability accountability, BudgetLine budgetline, String ops_man_review, String ceo_review, Long estimatedAmount, Long amountGranted, String status, Date dateCreated, Date maxDateNeeded, Date dateApproved) {
        this.id = id;
        this.justification = justification;
        this.user = user;
        this.accountability = accountability;
        this.budgetline = budgetline;
        this.ops_man_review = ops_man_review;
        this.ceo_review = ceo_review;
        this.estimatedAmount = estimatedAmount;
        this.amountGranted = amountGranted;
        this.status = status;
        this.dateCreated = dateCreated;
        this.maxDateNeeded = maxDateNeeded;
        this.dateApproved = dateApproved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Accountability getAccountability() {
        return accountability;
    }

    public void setAccountability(Accountability accountability) {
        this.accountability = accountability;
    }

    public BudgetLine getBudgetline() {
        return budgetline;
    }

    public void setBudgetline(BudgetLine budgetline) {
        this.budgetline = budgetline;
    }

    public String getOps_man_review() {
        return ops_man_review;
    }

    public void setOps_man_review(String ops_man_review) {
        this.ops_man_review = ops_man_review;
    }

    public String getCeo_review() {
        return ceo_review;
    }

    public void setCeo_review(String ceo_review) {
        this.ceo_review = ceo_review;
    }

    public Long getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(Long estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public Long getAmountGranted() {
        return amountGranted;
    }

    public void setAmountGranted(Long amountGranted) {
        this.amountGranted = amountGranted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getMaxDateNeeded() {
        return maxDateNeeded;
    }

    public void setMaxDateNeeded(Date maxDateNeeded) {
        this.maxDateNeeded = maxDateNeeded;
    }

    public Date getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
    }
}
