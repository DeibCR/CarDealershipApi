package com.pluralsight.dao;

import com.pluralsight.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface SalesContractRepository extends JpaRepository<SalesContract, Integer> {
}
