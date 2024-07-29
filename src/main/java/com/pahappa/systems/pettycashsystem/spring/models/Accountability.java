package com.pahappa.systems.pettycashsystem.spring.models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Entity
public class Accountability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean balanceIsReturned=false;

    private String description;

    @Column(nullable = false)
    private Boolean deleted=false;

    private String status;

    private String extraClaims;

    @Column(nullable = false)
    private Double amountSpent;

    ///////////////////////// modified this such that requisition is the parent
    @OneToOne(fetch = FetchType.EAGER)
    private Requisition requisition;

    @Temporal(TemporalType.DATE)
    private Date dateSubmitted;

    @Lob
    @Column(nullable = true)
    private byte[] receiptImage;

    public Accountability() {
    }

    private Accountability(Long id, Boolean deleted, String description, Boolean balanceIsReturned, String extraClaims, Double amountSpent, Requisition requisition, Date dateSubmitted, byte[] receiptImage) {
        this.id = id;
        this.description = description;
        this.deleted = deleted;
        this.balanceIsReturned = balanceIsReturned;
        this.extraClaims = extraClaims;
        this.amountSpent = amountSpent;
        this.requisition = requisition;
        this.dateSubmitted = dateSubmitted;
        this.receiptImage = receiptImage;
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

    public Boolean getBalanceIsReturned() {
        return balanceIsReturned;
    }

    public void setBalanceIsReturned(Boolean balanceIsReturned) {
        this.balanceIsReturned = balanceIsReturned;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtraClaims() {
        return extraClaims;
    }

    public void setExtraClaims(String extraClaims) {
        this.extraClaims = extraClaims;
    }

    public Double getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(Double amountSpent) {
        this.amountSpent = amountSpent;
    }

    public Requisition getRequisition() {
        return requisition;
    }

    public void setRequisition(Requisition requisition) {
        this.requisition = requisition;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public byte[] getReceiptImage() {
        return receiptImage;
    }

    public void setReceiptImage(byte[] receiptImage) {
        this.receiptImage = receiptImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accountability that = (Accountability) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getExtraClaims(), that.getExtraClaims()) && Objects.equals(getAmountSpent(), that.getAmountSpent()) && Objects.equals(getRequisition(), that.getRequisition()) && Objects.equals(getDateSubmitted(), that.getDateSubmitted()) && Objects.deepEquals(getReceiptImage(), that.getReceiptImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getExtraClaims(), getAmountSpent(), getRequisition(), getDateSubmitted(), Arrays.hashCode(getReceiptImage()));
    }

    @Override
    public String toString() {
        return "Accountability{" +
                "id=" + id +
                ", extraClaims='" + extraClaims + '\'' +
                ", amountSpent=" + amountSpent +
                ", description='" + description + '\'' +
                '}';
    }


}
