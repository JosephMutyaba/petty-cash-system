package com.pahappa.systems.pettycashsystem.spring.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Requisition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String justification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Accountability accountability;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budgetline_id", nullable = false)
    private BudgetLine budgetline;

    private String ops_man_review;
    private String ceo_review;

    @Column(nullable = false)
    private Double estimatedAmount;


    private Double amountGranted = estimatedAmount; // amount granted is by default equal to estimated amount

    @Column(nullable = false)
    private String status="NEW";

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(nullable = false)
    private Date maxDateNeeded;

    private Date dateApproved;

    public Requisition() {
    }

    private Requisition(Long id, String justification, User user, Accountability accountability, BudgetLine budgetline, String ops_man_review, String ceo_review, Double estimatedAmount, Double amountGranted, String status, Date dateCreated, Date maxDateNeeded, Date dateApproved) {
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
        return Objects.equals(getId(), that.getId()) && Objects.equals(getJustification(), that.getJustification()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getAccountability(), that.getAccountability()) && Objects.equals(getBudgetline(), that.getBudgetline()) && Objects.equals(getOps_man_review(), that.getOps_man_review()) && Objects.equals(getCeo_review(), that.getCeo_review()) && Objects.equals(getEstimatedAmount(), that.getEstimatedAmount()) && Objects.equals(getAmountGranted(), that.getAmountGranted()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getDateCreated(), that.getDateCreated()) && Objects.equals(getMaxDateNeeded(), that.getMaxDateNeeded()) && Objects.equals(getDateApproved(), that.getDateApproved());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getJustification(), getUser(), getAccountability(), getBudgetline(), getOps_man_review(), getCeo_review(), getEstimatedAmount(), getAmountGranted(), getStatus(), getDateCreated(), getMaxDateNeeded(), getDateApproved());
    }

    @Override
    public String toString() {
        return "Requisition{" +
                "id=" + id +
                ", justification='" + justification + '\'' +
                ", user=" + user +
                ", accountability=" + accountability +
                ", budgetline=" + budgetline +
                ", ops_man_review='" + ops_man_review + '\'' +
                ", ceo_review='" + ceo_review + '\'' +
                ", estimatedAmount=" + estimatedAmount +
                ", amountGranted=" + amountGranted +
                ", status='" + status + '\'' +
                ", dateCreated=" + dateCreated +
                ", maxDateNeeded=" + maxDateNeeded +
                ", dateApproved=" + dateApproved +
                '}';
    }
}
