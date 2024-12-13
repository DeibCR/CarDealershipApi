package com.pluralsight.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ResourceBundle;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;


@Entity
@Table(name= "sales_contracts")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalesContract extends Contract {

    @Column(name = "sales_tax")
    private BigDecimal sales_tax;
    @Column(name = "recording_fee")
    private BigDecimal recording_fee;
    @Column(name = "processing_fee")
    private BigDecimal processing_fee;
    @Column(name = "is_finance")
    private Boolean is_finance;




    private static final ResourceBundle resourceBundle= ResourceBundle.getBundle("contract_config");



    public SalesContract() {
    }

    public BigDecimal getSales_tax() {

        return sales_tax;
    }



    public BigDecimal getRecording_fee() {

        return recording_fee;
    }


    public void setSales_tax(BigDecimal sales_tax) {

        this.sales_tax = sales_tax;
    }

    public void setRecording_fee(BigDecimal recording_fee) {

        this.recording_fee = recording_fee;
    }

    public void setProcessing_fee(BigDecimal processing_fee) {
        this.processing_fee = processing_fee;
    }

    public void setFinance(Boolean finance) {
        is_finance = finance;
    }

    public BigDecimal getProcessing_fee() {

        return processing_fee;
    }


    public Boolean  isFinance() {
        return is_finance;
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
       // return String.format("SALE|%s|%s|%s|%d|%s|%d|%.2f|%.2f|%.2f|%.2f|%b",
               //// getDateOfContract(), getCustomerName(), getCustomerEmail(),
               // getVehicleSold().getVin(), getVehicleSold().getMake(),
                //getVehicleSold().getYear(), salesTax, recordingFee, processingFee, getTotalPrice(), isFinance);
        Vehicle vehicle = getVehicleSold();
        return String.format("SALE|%s|%s|%s|%s|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                getDate(),
                getName(),
                getEmail(),
                vehicle.getVin(),
                vehicle.getYear(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getType(),
                vehicle.getColor(),
                vehicle.getOdometer(),
                vehicle.getPrice(),
                getSales_tax(),
                getRecording_fee(),
                getProcessing_fee(),
                getTotalPrice(),
                "NO",
                0.00 );
    }
}

