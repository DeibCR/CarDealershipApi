package com.pluralsight.dao;

import model.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOMysqlJdbc implements VehicleDAO{

    public final DataSource dataSource;

    public VehicleDAOMysqlJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }




    @Override
    public List<Vehicle> findVehicleByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehiclesByPrice= new ArrayList<>();


        String query= """
                SELECT *
                FROM vehicles
                WHERE price BETWEEN ? AND ?
                """;
        try(Connection connection=dataSource.getConnection()){
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setDouble(1,minPrice);
            ps.setDouble(2,maxPrice);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                String vin =rs.getString("vin");
                int year=rs.getInt("year");
                String make=rs.getString("make");
                String model=rs.getString("model");
                String vehicleType=rs.getString("vehicleType");
                String color=rs.getString("color");
                int odometer=rs.getInt("odometer");
                double price=rs.getDouble("price");
                boolean sold=rs.getBoolean("sold");
                vehiclesByPrice.add(new Vehicle(vin,year,make,model,vehicleType,color,odometer,price,sold));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return vehiclesByPrice;
    }

    @Override
    public List<Vehicle> findVehicleByMakeModel(String make, String model) {
        List<Vehicle> vehiclesByMakeModel=new ArrayList<>();

        String query = """
                SELECT * FROM vehicles
                WHERE  make = ? AND model = ?
                """;
        try(Connection connection=dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, make);
            ps.setString(2, model);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
            String vin = rs.getString("vin");
            int year = rs.getInt("year");
            String vehicleMake = rs.getString("make");
            String vehicleModel = rs.getString("model");
            String vehicleType = rs.getString("vehicleType");
            String color = rs.getString("color");
            int odometer = rs.getInt("odometer");
            double price = rs.getDouble("price");
            boolean sold = rs.getBoolean("sold");
            vehiclesByMakeModel.add(new Vehicle(vin, year, vehicleMake,vehicleModel, vehicleType, color, odometer, price, sold));
        }

        }catch (SQLException e){
            e.printStackTrace();
        }


        return vehiclesByMakeModel;
    }

    @Override
    public List<Vehicle> findVehicleByYear(int maxYear, int minYear) {
        List<Vehicle> vehiclesByYearRange= new ArrayList<>();
        String query= """
                SELECT *
                FROM vehicles
                WHERE year BETWEEN ? AND ?
                """;
        try(Connection connection=dataSource.getConnection()){
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setDouble(1,maxYear);
            ps.setDouble(2,minYear);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                String vin =rs.getString("vin");
                int year=rs.getInt("year");
                String make=rs.getString("make");
                String model=rs.getString("model");
                String vehicleType=rs.getString("vehicleType");
                String color=rs.getString("color");
                int odometer=rs.getInt("odometer");
                double price=rs.getDouble("price");
                boolean sold=rs.getBoolean("sold");
                vehiclesByYearRange.add(new Vehicle(vin,year,make,model,vehicleType,color,odometer,price,sold));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return vehiclesByYearRange;

    }

    @Override
    public List<Vehicle> findVehicleByColor(String color) {

        List<Vehicle> vehiclesByColor= new ArrayList<>();
        String query= """
                SELECT *
                FROM vehicles
                WHERE color = ?
                """;
        try(Connection connection=dataSource.getConnection()){
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setString(1,color);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                String vin =rs.getString("vin");
                int year=rs.getInt("year");
                String make=rs.getString("make");
                String model=rs.getString("model");
                String vehicleType=rs.getString("vehicleType");
                String vehicleColor=rs.getString("color");
                int odometer=rs.getInt("odometer");
                double price=rs.getDouble("price");
                boolean sold=rs.getBoolean("sold");
                vehiclesByColor.add(new Vehicle(vin,year,make,model,vehicleType,vehicleColor,odometer,price,sold));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return vehiclesByColor;
    }

    @Override
    public List<Vehicle> findVehicleByMileage(int minOdometer, int maxOdometer) {
        List<Vehicle> vehiclesByMileageRange= new ArrayList<>();


        String query= """
                SELECT *
                FROM vehicles
                WHERE odometer BETWEEN ? AND ?
                """;
        try(Connection connection=dataSource.getConnection()){
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setDouble(1,minOdometer);
            ps.setDouble(2,maxOdometer);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                String vin =rs.getString("vin");
                int year=rs.getInt("year");
                String make=rs.getString("make");
                String model=rs.getString("model");
                String vehicleType=rs.getString("vehicleType");
                String color=rs.getString("color");
                int odometer=rs.getInt("odometer");
                double price=rs.getDouble("price");
                boolean sold=rs.getBoolean("sold");
                vehiclesByMileageRange.add(new Vehicle(vin,year,make,model,vehicleType,color,odometer,price,sold));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return vehiclesByMileageRange;
    }

    @Override
    public List<Vehicle> findVehicleByType(String vehicleType) {
        List<Vehicle> vehiclesByType= new ArrayList<>();
        String query= """
                SELECT *
                FROM vehicles
                WHERE vehicleType = ?
                """;
        try(Connection connection=dataSource.getConnection()){
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setString(1,vehicleType);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                String vin =rs.getString("vin");
                int year=rs.getInt("year");
                String make=rs.getString("make");
                String model=rs.getString("model");
                String type=rs.getString("vehicleType");
                String vehicleColor=rs.getString("color");
                int odometer=rs.getInt("odometer");
                double price=rs.getDouble("price");
                boolean sold=rs.getBoolean("sold");
                vehiclesByType.add(new Vehicle(vin,year,make,model,type,vehicleColor,odometer,price,sold));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return vehiclesByType;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        String query = """
                INSERT INTO vehicles (vin, year, make, model, vehicleType, color, odometer, price, sold)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try (Connection connection=dataSource.getConnection();
        PreparedStatement ps= connection.prepareStatement(query)){
            ps.setString(1, vehicle.getVin());
            ps.setInt(2, vehicle.getYear());
            ps.setString(3, vehicle.getMake());
            ps.setString(4, vehicle.getModel());
            ps.setString(5, vehicle.getVehicleType());
            ps.setString(6, vehicle.getColor());
            ps.setInt(7, vehicle.getOdometer());
            ps.setDouble(8, vehicle.getPrice());
            ps.setBoolean(9, vehicle.isSold());
            ps.executeUpdate();

        } catch (SQLException e) {
        e.printStackTrace();
    }

    }

    @Override
    public boolean removeVehicle(String vin) {
        String query = """
                DELETE FROM vehicles
                WHERE vin = ? 
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1,vin);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public List<Vehicle> findAllVehicles() {
        List<Vehicle> vehicles=new ArrayList<>();
        String query = """
                SELECT * 
                FROM vehicles
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String vin = rs.getString("vin");
                int year = rs.getInt("year");
                String make = rs.getString("make");
                String model = rs.getString("model");
                String vehicleType = rs.getString("vehicleType");
                String color = rs.getString("color");
                int odometer = rs.getInt("odometer");
                double price = rs.getDouble("price");
                boolean sold = rs.getBoolean("sold");

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price, sold);
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
        e.printStackTrace();
    }

        return vehicles;
    }

    @Override
    public Vehicle findVehicleByVin(String vin) {
        String query = "SELECT * FROM vehicles WHERE vin = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, vin);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Vehicle(
                        resultSet.getString("vin"),
                        resultSet.getInt("year"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getString("VehicleType"),
                        resultSet.getString("color"),
                        resultSet.getInt("odometer"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("sold")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database error: Unable to find vehicle by VIN.");
        }

        return null;
    }


}
