package com.pharmacy.ordermanagement.models;

public class OrderedDrug {
    private String drugId;
    private int drugQuantity;

    public OrderedDrug() {
    }

    public OrderedDrug(String drugId, int drugQuantity) {
        this.drugId = drugId;
        this.drugQuantity = drugQuantity;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public int getDrugQuantity() {
        return drugQuantity;
    }

    public void setDrugQuantity(int drugQuantity) {
        this.drugQuantity = drugQuantity;
    }
}
