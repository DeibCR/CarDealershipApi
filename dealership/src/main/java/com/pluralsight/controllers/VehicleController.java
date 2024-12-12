package com.pluralsight.controllers;

import com.pluralsight.dao.VehicleRepository;
import com.pluralsight.model.Vehicle;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Repository;

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
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
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

}

