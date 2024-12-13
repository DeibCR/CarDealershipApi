package com.pluralsight.controllers;

import com.pluralsight.dao.VehicleRepository;
import com.pluralsight.model.Vehicle;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(path="/rest/vehicles")
public class VehicleController {
    private final VehicleRepository vehicleRepository;


    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    @GetMapping("/search")
    public List<Vehicle> searchVehicles(
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer minOdometer,
            @RequestParam(required = false) Integer maxOdometer,
            @RequestParam(required = false) String type) {
        System.out.println("Searching for vehicles with make: " + make);
        return vehicleRepository.searchBy(minPrice, maxPrice, make, model, minYear, maxYear, color, type, minOdometer, maxOdometer);
    }

    @GetMapping
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @PostMapping
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @GetMapping("/findByVin")
    public Vehicle findVehicleByVin(@RequestParam Integer vin) {
        return vehicleRepository.findVehicleByVin(vin);
    }

    @PutMapping("/update/{vin}")
    public Vehicle updateVehicle(@PathVariable Integer vin, @RequestBody Vehicle updatedVehicle){
        Vehicle registerVehicle= vehicleRepository.findVehicleByVin(vin);
        if (registerVehicle != null){
            registerVehicle.setMake(updatedVehicle.getMake());
            registerVehicle.setType(updatedVehicle.getModel());
            registerVehicle.setYear(updatedVehicle.getYear());
            registerVehicle.setColor(updatedVehicle.getColor());
            registerVehicle.setPrice(updatedVehicle.getPrice());
            registerVehicle.setOdometer(updatedVehicle.getOdometer());
            registerVehicle.setType(updatedVehicle.getType());


            return vehicleRepository.save(registerVehicle);
        }else {
            return null;
        }
    }

    @DeleteMapping("/delete/{vin}")
    public String deleteVehicle(@PathVariable Integer vin){
        Vehicle vehicle=vehicleRepository.findVehicleByVin(vin);

        if (vehicle != null){
            vehicleRepository.delete(vehicle);
            return "Vehicle with VIN: " + vin + " has been deleted successfully";
        }else {
            return "Vehicle with VIN: " + vin + " not found";
        }
    }

}

