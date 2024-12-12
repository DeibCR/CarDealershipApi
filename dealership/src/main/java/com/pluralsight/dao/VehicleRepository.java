package com.pluralsight.dao;

import com.pluralsight.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository  extends JpaRepository<Vehicle,String> {
    List<Vehicle> findByMake(String make);


    List<Vehicle> findByModel(String model);
    List<Vehicle> findByColor(String color);
    List<Vehicle> findByType(String type);




    @Query("SELECT v FROM Vehicle v WHERE " +
            "(:minPrice IS NULL OR v.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR v.price <= :maxPrice) AND " +
            "(:make IS NULL OR v.make = :make) AND " +
            "(:model IS NULL OR v.model = :model) AND " +
            "(:minYear IS NULL OR v.year >= :minYear) AND " +
            "(:maxYear IS NULL OR v.year <= :maxYear) AND " +
            "(:color IS NULL OR v.color = :color) AND " +
            "(:minOdometer IS NULL OR v.odometer >= :minOdometer) AND " +
            "(:maxOdometer IS NULL OR v.odometer <= :maxOdometer) AND " +
            "(:type IS NULL OR v.type = :type)")
    List<Vehicle> searchBy(
            Double minPrice,
            Double maxPrice,
            String make,
            String model,
            Integer minYear,
            Integer maxYear,
            String color,
            String type,
            Integer minOdometer,
            Integer maxOdometer);

    Vehicle findVehicleByVin(Integer vin);



}
