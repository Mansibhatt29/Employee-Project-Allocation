package com.example.FinalProjectAllocation.repositories;

import com.example.FinalProjectAllocation.entities.RoleRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRequestRepo extends JpaRepository<RoleRequestEntity, Long> {


    List<RoleRequestEntity> findByStatus(String status);

    Long countByStatus(String status);

    List<RoleRequestEntity> findByManagerEmpId(Long managerEmpId);

}
