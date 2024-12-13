package com.pluralsight.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name= "contract")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "contractType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LeaseContract.class, name = "lease"),
        @JsonSubTypes.Type(value = SalesContract.class, name = "sales")
})

public abstract class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contractID")
    private  Integer contractID;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "name")
    private   String name;
    @Column(name = "email")
    private  String email;
    @ManyToOne
    @JoinColumn(name="vin", nullable = false)
    private  Vehicle vehicleSold;



    public Contract(Integer contractID,LocalDate dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.contractID=contractID;
        this.date = dateOfContract;
        this.name = customerName;
        this.email = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public Contract() {
    }

    public Integer getContractID() {
        return contractID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public abstract Double getTotalPrice();

    public abstract Double getMonthlyPayment();

    public abstract String  getRepresentation();
}
