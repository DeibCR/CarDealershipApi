package com.pluralsight.controllers;

import com.pluralsight.dao.*;
import com.pluralsight.model.Contract;
import com.pluralsight.model.LeaseContract;
import com.pluralsight.model.SalesContract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(path="/rest/contracts")
public class ContractController {

    private final ContractRepository contractRepository;
    private final LeaseContractRepository leaseContractRepository;
    private final SalesContractRepository salesContractRepository;

    private final VehicleRepository vehicleRepository;

    public ContractController(ContractRepository contractRepository, LeaseContractRepository leaseContractRepository, SalesContractRepository salesContractRepository, VehicleRepository vehicleRepository) {
        this.contractRepository = contractRepository;
        this.leaseContractRepository = leaseContractRepository;
        this.salesContractRepository = salesContractRepository;
        this.vehicleRepository = vehicleRepository;

    }

    @GetMapping("/{contractID}")
    public Object getContractsById(@PathVariable Integer contractID) {
        return contractRepository.findById(contractID)
                .map(contract -> {
                    if (contract instanceof LeaseContract) {
                        LeaseContract leaseContract = (LeaseContract) contract;

                        if (leaseContract.getExpected_ending_value_percentage() == null) {
                            leaseContract.setExpected_ending_value_percentage(BigDecimal.valueOf(0.0));
                        }
                        if (leaseContract.getLease_fee() == null) {
                            leaseContract.setLease_fee(BigDecimal.valueOf(0.0));
                        }
                        return contract;

                    } else if (contract instanceof SalesContract) {
                        SalesContract salesContract = (SalesContract) contract;

                        if (salesContract.getSales_tax() == null) {
                            salesContract.setSales_tax(BigDecimal.valueOf(0.0));
                        }
                        if (salesContract.getProcessing_fee() == null) {
                            salesContract.setProcessing_fee(BigDecimal.valueOf(0.0));
                        }
                        if (salesContract.getRecording_fee() == null) {
                            salesContract.setRecording_fee(BigDecimal.valueOf(0.0));
                        }
                        return contract;

                    } else {
                        return "Unknown contract type";
                    }
                })
                .orElse("Contract not found");
    }

    @PostMapping
    public ResponseEntity<String> addContract(@RequestBody ContractWrapper contractWrapper){
        Contract contract= contractWrapper.getContract();


        contractRepository.save(contract);

        if (contract instanceof LeaseContract) {
            LeaseContract leaseContract = (LeaseContract) contract;
            leaseContractRepository.save(leaseContract);
        } else if (contract instanceof SalesContract) {
            SalesContract salesContract = (SalesContract) contract;
            salesContractRepository.save(salesContract);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid contract type.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Contract added successfully.");
    }


}
    /*
    @PostMapping
    public String addContract(@RequestBody Contract contractRequest){
        //Use the id from the request body to look for the vehicle

        Vehicle vehicleFromRequest= contractRequest.getVehicleSold();

        Integer vehicleVin= vehicleFromRequest.getVin();

        Optional<Vehicle> vehicleOptional= vehicleRepository.findByVin(vehicleVin);

        if (vehicleOptional.isEmpty()){
            return "Vehicle with vin: " +vehicleVin + " not found";
        }

        Vehicle vehicle= vehicleOptional.get();
        contractRequest.setVehicleSold(vehicle);


        //to check the type of contract
        if (contractRequest instanceof LeaseContract){
            LeaseContract leaseContract= (LeaseContract) contractRequest;

            if (leaseContract.getExpected_ending_value_percentage() == null){
                leaseContract.setExpected_ending_value_percentage(BigDecimal.valueOf(0.0));
            }
            if (leaseContract.getLease_fee() == null){
                leaseContract.setLease_fee(BigDecimal.valueOf(0.0));
            }

            //with this assign the vehicle to the contract
            ;

            //save the lease contract

            contractRepository.save(leaseContract);
            return "Lease contract created successfully";

        } else if (contractRequest instanceof SalesContract) {
            SalesContract salesContract= (SalesContract) contractRequest;

            if (salesContract.getSales_tax() == null){
                salesContract.setSales_tax(BigDecimal.valueOf(0.0));
            }
            if (salesContract.getProcessing_fee() == null){
                salesContract.setProcessing_fee(BigDecimal.valueOf(0.0));
            }
            if (salesContract.getRecording_fee() == null){
                salesContract.setRecording_fee(BigDecimal.valueOf(0.0));
            }


            contractRepository.save(salesContract);
            return "Sales Contract created succesfully";
        } else {
            return "Invalid contract type";
        }
    }

     */






