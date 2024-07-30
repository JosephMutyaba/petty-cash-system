package com.pahappa.systems.pettycashsystem.managedcontroller.requisitions;

import javax.inject.Named;

@Named
public class RequisitionStatusColors {

    public String getStatusColor(String status) {
        switch (status) {
            case "Approved":
                return " text-green-400 ";
            case "Rejected":
                return " text-red-400 ";
            case "Reviewed":
                return " text-blue-400 ";
            case "RequestEdit":
                return " text-orange-400 ";
            case "Pending":
                return " text-purple-400 ";
            case "Paid":
                return " text-yellow-600 ";
            default:
                return " text-blk ";
        }
    }
}
