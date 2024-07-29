package com.pahappa.systems.pettycashsystem.spring.enums;

public enum ModifyStatus {
    Null(null,"Select Status"),
    APPROVE("Approved","Approve"),
    ACCEPT("Accepted","Review"),
    REJECT("Rejected","Reject"),
    REQUEST_CHANGES("RequestEdit","Request Changes");

    final String status;
    final String description;

    ModifyStatus(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
