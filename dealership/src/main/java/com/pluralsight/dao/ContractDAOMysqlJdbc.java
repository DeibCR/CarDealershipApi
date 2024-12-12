package com.pluralsight.dao;

import com.pluralsight.model.*;

import javax.sql.DataSource;
import java.sql.*;

public class ContractDAOMysqlJdbc implements ContractDAO{

    private final DataSource dataSource;

    public ContractDAOMysqlJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public boolean addContract(Contract contract) {
        String insertContractQuery = "INSERT INTO contract (contractID, dateOfContract, customerName, customerEmail, vin) VALUES (?, ?, ?, ?, ?)";
        String insertSalesContractQuery = "INSERT INTO sales_contracts (contractID, salesTax, recordingFee, processingFee, isFinance, vin) VALUES (?, ?, ?, ?, ?, ?)";
        String insertLeaseContractQuery = "INSERT INTO lease_contracts (contractID, expectingEndingValuePercentage, leaseFee, vin) VALUES (?, ?, ?, ?)";

        try (Connection connection=dataSource.getConnection()){
            connection.setAutoCommit(false);
            try (PreparedStatement contractSt=connection.prepareStatement(insertContractQuery)){
                contractSt.setInt(1,contract.getContractID());
                contractSt.setDate(2,Date.valueOf(contract.getDateOfContract()));
                contractSt.setString(3,contract.getCustomerName());
                contractSt.setString(4,contract.getCustomerEmail());
                contractSt.setInt(5,contract.getVehicleSold().getVin());
                contractSt.executeUpdate();

            }

            if (contract instanceof SalesContract){
                try (PreparedStatement salesSt= connection.prepareStatement(insertSalesContractQuery)){
                    SalesContract salesContract =(SalesContract) contract;
                    salesSt.setInt(1,salesContract.getContractID());
                    salesSt.setDouble(2,salesContract.getSalesTax());
                    salesSt.setDouble(3,salesContract.getRecordingFee());
                    salesSt.setDouble(4,salesContract.getProcessingFee());
                    salesSt.setBoolean(5,salesContract.isFinance());
                    salesSt.setInt(6,salesContract.getVehicleSold().getVin());
                    salesSt.executeUpdate();

                }
            } else if (contract instanceof LeaseContract) {
                try(PreparedStatement leaseSt=connection.prepareStatement(insertLeaseContractQuery)){
                    LeaseContract leaseContract=(LeaseContract) contract;
                    leaseSt.setInt(1,leaseContract.getContractID());
                    leaseSt.setDouble(2,leaseContract.getExpectedEndingValuePercentage());
                    leaseSt.setDouble(3,leaseContract.getLeaseFee());
                    leaseSt.setInt(4,leaseContract.getVehicleSold().getVin());
                    leaseSt.executeUpdate();
                }

            }
            connection.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public void deleteContract(int contractID) {

    }

    @Override
    public Contract findContractById(int contractID) {
        return null;
    }
}
