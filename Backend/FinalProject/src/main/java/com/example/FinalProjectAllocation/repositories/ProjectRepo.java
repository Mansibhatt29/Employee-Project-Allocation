package com.example.FinalProjectAllocation.repositories;

import com.example.FinalProjectAllocation.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepo extends JpaRepository<ProjectEntity, Long> {

    List<ProjectEntity> findByEmployeesIsEmpty();

    /*@Query("SELECT p FROM ProjectEntity p WHERE " +
            "(SELECT COUNT(e) FROM EmployeeEntity e WHERE e.projectEntity.projectId = p.projectId) < p.capacity")/*
     */
    @Query(value= "SELECT * FROM project WHERE " +
            "(SELECT COUNT(*) FROM employee WHERE employee.projectid = project.projectid) < project.capacity",
            nativeQuery = true)
    List<ProjectEntity> findUnderstaffedProjects();
}
