package com.rentbook.demo.dto.response;

public class RentalReturnResponse {

    private double totalCost;

    public RentalReturnResponse(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
