package com.pluralsight.model;

public abstract class Contract {
    private final int contractID;
    private String dateOfContract;
    private   String customerName;
    private  String customerEmail;
    private final Vehicle vehicleSold;



    public Contract(int contractID,String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.contractID=contractID;
        this.dateOfContract = dateOfContract;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public int getContractID() {
        return contractID;
    }

    public String getDateOfContract() {
        return dateOfContract;
    }

    public void setDateOfContract(String dateOfContract) {
        this.dateOfContract = dateOfContract;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    public abstract String  getRepresentation();
}
