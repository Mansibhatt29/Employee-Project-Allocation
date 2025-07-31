package com.example.FinalProjectAllocation.repositories;

import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {

    //List<EmployeeEntity> findByProjectId(Long projectId);
    EmployeeEntity findByEmpId(Long empId);

    List<EmployeeEntity> findByProjectEntityIsNull();

    List<EmployeeEntity> findByDesignation(String designation);

    @Query(value = "SELECT * FROM employee WHERE is_available = true AND designation = :designation", nativeQuery = true)
    List<EmployeeEntity> findAvailableEmployeesByDesignation(String designation);

    List<EmployeeEntity> findByAvailabilityTrue();

//    List<EmployeeEntity> findByDesignationAndProjectIsNull(String requestedDesignation);
}

