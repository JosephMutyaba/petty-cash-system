package com.pahappa.systems.pettycashsystem.spring.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Requisition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean deleted = false;

    private String justification;

    @ManyToOne
    private User user;

    //// MADE REQUISITION THE PARENT ENTITY FOR ACCOUNTABILITY
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "accountability_id")
    private Accountability accountability;

    private Date dateAccountabilityWasSubmitted;

    @ManyToOne
    private BudgetLine budgetline;

    private String review_comments;

    @Column(nullable = false)
    private Double estimatedAmount;


    private Double amountGranted = 0.0; // amount granted is by default equal to 0.0

    @Column(nullable = false)
    private String status="NEW";

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(nullable = false)
    private Date maxDateNeeded;

    private Date dateApproved;

    private Date dateOfCashDisbursement;

    public Requisition() {
    }

    private Requisition(Long id, Boolean deleted, Date dateOfCashDisbursement, String justification, Date dateAccountabilityWasSubmitted, User user, Accountability accountability, BudgetLine budgetline, String review_comments, Double estimatedAmount, Double amountGranted, String status, Date dateCreated, Date maxDateNeeded, Date dateApproved) {
        this.id = id;
        this.deleted=deleted;
        this.dateOfCashDisbursement=dateOfCashDisbursement;
        this.justification = justification;
        this.dateAccountabilityWasSubmitted=dateAccountabilityWasSubmitted;
        this.user = user;
        this.accountability = accountability;
        this.budgetline = budgetline;
        this.estimatedAmount = estimatedAmount;
        this.amountGranted = amountGranted;
        this.review_comments=review_comments;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getDateOfCashDisbursement() {
        return dateOfCashDisbursement;
    }

    public void setDateOfCashDisbursement(Date dateOfCashDisbursement) {
        this.dateOfCashDisbursement = dateOfCashDisbursement;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public Date getDateAccountabilityWasSubmitted() {
        return dateAccountabilityWasSubmitted;
    }

    public void setDateAccountabilityWasSubmitted(Date dateAccountabilityWasSubmitted) {
        this.dateAccountabilityWasSubmitted = dateAccountabilityWasSubmitted;
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

    public String getReview_comments() {
        return review_comments;
    }

    public void setReview_comments(String review_comments) {
        this.review_comments = review_comments;
    }

    public Double getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(Double estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public Double getAmountGranted() {
        return amountGranted;
    }

    public void setAmountGranted(Double amountGranted) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Requisition that = (Requisition) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getJustification(), that.getJustification()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getAccountability(), that.getAccountability()) && Objects.equals(getBudgetline(), that.getBudgetline()) && Objects.equals(getReview_comments(), that.getReview_comments()) && Objects.equals(getEstimatedAmount(), that.getEstimatedAmount()) && Objects.equals(getAmountGranted(), that.getAmountGranted()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getMaxDateNeeded(), that.getMaxDateNeeded()) && Objects.equals(getDateApproved(), that.getDateApproved());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getJustification(), getUser(), getAccountability(), getBudgetline(), getReview_comments(), getEstimatedAmount(), getAmountGranted(), getStatus(), getDateCreated(), getMaxDateNeeded(), getDateApproved());
    }

    @Override
    public String toString() {
        return "#REQ-" + id;
    }
}
