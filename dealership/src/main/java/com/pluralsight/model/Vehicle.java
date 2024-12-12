package com.pluralsight.model;

import jakarta.persistence.*;

@Entity
@Table(name= "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vin")
    private  Integer vin;


    @Column(name="year")
    private  Integer year;

    @Column(name = "make")
    private  String make;

    @Column(name = "model")
    private  String model;

    @Column(name="type")
    private  String type;

    @Column(name="color")
    private  String color;

    @Column(name="odometer")
    private  Integer odometer;

    @Column(name="price")
    private  Double price;

    @Column(name="sold")
    private  boolean sold;

    public Vehicle() {
    }

    public Vehicle(Integer vin, int year, String make, String model, String type, String color, int odometer, double price, boolean sold) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.type = type;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
        this.sold=sold;
    }

    public void setVin(Integer vin) {
        this.vin = vin;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOdometer(Integer odometer) {
        this.odometer = odometer;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getVin() {
        return vin;
    }

    public Integer getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public Integer getOdometer() {
        return odometer;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return String.format("%-8s %-8s %-12s %-12s %-8s %-8s %-8s $%8.2f", vin, year, make, model, type, color, odometer, price);
    }
}
