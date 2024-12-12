package com.pluralsight.dao;

import com.pluralsight.model.Dealership;

import java.util.List;

public interface DealershipDAO {
    Dealership findDealershipById(int id);
    List<Dealership> findAllDealerships();



}
