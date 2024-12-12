package com.pluralsight.dao;

import com.pluralsight.model.Contract;

public interface ContractDAO {
    boolean addContract(Contract contract);
    void deleteContract(int contractID);
    Contract findContractById(int contractID);
}
