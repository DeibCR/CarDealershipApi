package com.pluralsight.model;

import java.math.BigDecimal;
import java.util.ResourceBundle;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name= "lease_contracts")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class LeaseContract extends Contract {

    @Column(name = "expected_ending_value_percentage")
    private BigDecimal expected_ending_value_percentage;


    @Column(name = "lease_fee")
    private  BigDecimal lease_fee;




    private static final ResourceBundle resourceBundle= ResourceBundle.getBundle("contract_config");// Using resource bundle to provide flexibility and maintainability


    public LeaseContract() {
    }


    public BigDecimal getExpected_ending_value_percentage() {

        return expected_ending_value_percentage;
    }

    public void setExpected_ending_value_percentage(BigDecimal expected_ending_value_percentage) {

        this.expected_ending_value_percentage = expected_ending_value_percentage;
    }

    public void setLease_fee(BigDecimal lease_fee) {
        this.lease_fee = lease_fee;
    }

    public BigDecimal getLease_fee() {

        return lease_fee;
    }

    @Override
    public Double getTotalPrice() {
        return null;
    }

    @Override
    public Double getMonthlyPayment() {

        return null;
    }

    @Override
    public String getRepresentation() {
        //return String.format("LEASE|%s|%s|%s|%s|%.2f|%.2f|%.2f",
               // getDateOfContract(), getCustomerName(), getCustomerEmail(),
               // getVehicleSold().toString(), expectedEndingValuePercentage, leaseFee, getTotalPrice());
        return String.format("LEASE|%s|%s|%s|%s|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f",
                getDate(),
                getName(),
                getEmail(),
                getVehicleSold().getVin(),
                getVehicleSold().getYear(),
                getVehicleSold().getMake(),
                getVehicleSold().getModel(),
                getVehicleSold().getType(),
                getVehicleSold().getColor(),
                getVehicleSold().getOdometer(),
                getVehicleSold().getPrice(),
                getExpected_ending_value_percentage(),
                getLease_fee(),
                getTotalPrice(),
                getMonthlyPayment());
    }
}
