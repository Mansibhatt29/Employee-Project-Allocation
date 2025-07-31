package com.example.FinalProjectAllocation.repositories;

import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import com.example.FinalProjectAllocation.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findByUsername(String username);

    UserDetails findByEmployee(EmployeeEntity employee);

}

