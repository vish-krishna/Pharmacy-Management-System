package com.pharmacy.ordermanagement.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Orders {
    @Id
    private String orderId;
    private String doctorId;

    @ManyToMany(mappedBy = "orders", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Drug> drugList;

    private double totalPrice;
    private boolean pickedUp;
    private boolean verified;

    public Orders() {
    }

    public Orders(String orderId, List<Drug> drugList, String doctorId, double totalPrice, boolean pickedUp, boolean verified) {
        this.orderId = orderId;
        this.drugList = drugList;
        this.doctorId = doctorId;
        this.totalPrice = totalPrice;
        this.pickedUp = pickedUp;
        this.verified = verified;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}