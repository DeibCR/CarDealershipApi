package model;

import com.mysql.cj.jdbc.MysqlDataSource;
import dao.VehicleDAOMysqlJdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;


public class Dealership {
    private final int dealershipID;
    private final String name;
    private final String address;
    private final String phone;





    public Dealership(int dealershipID,String name, String address, String phone) {
        this.dealershipID=dealershipID;
        this.name = name;
        this.address = address;
        this.phone = phone;




    }



    public List<Vehicle> getAllVehicles(){


        return null;
    }

    public void addVehicle(Vehicle vehicle){

    }

    public void removeVehicle(Vehicle vehicle){

    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        return null;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return null;
    }

    public List<Vehicle> getVehiclesByYear(int maxYear, int minYear) {
        return null;
    }


    public List<Vehicle> getVehiclesByColor(String color){
        return null;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        return null;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        return null;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void addContract(Contract contract) {

    }

    public List<Contract> getAllContracts() {
    return null;
    }

    public int getDealershipID() {
        return dealershipID;
    }
}


