package dao;

import model.Contract;

public interface ContractDAO {
    boolean addContract(Contract contract);
    void deleteContract(int contractID);
    Contract findContractById(int contractID);
}
