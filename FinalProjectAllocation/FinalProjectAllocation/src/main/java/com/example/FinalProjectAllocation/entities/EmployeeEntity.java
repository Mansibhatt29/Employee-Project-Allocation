package com.example.FinalProjectAllocation.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empid")
    private Long empId;

    @Column(name="empname")
    private String empName;

    private String designation;

    //@JsonProperty("isAvailable")
    @Column(name="is_Available")
    private boolean availability;

    @ManyToOne
    @JoinColumn(name = "projectid", nullable = true)
    private ProjectEntity projectEntity;

    @Column(name = "skill_set")
    private String skillSet;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private UserDetails user;

    @Column(name = "email")
    private String email;

    @Transient
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", designation='" + designation + '\'' +
                ", availability=" + availability +
                ", projectEntity=" + projectEntity +
                ", skillSet='" + skillSet + '\'' +
                '}';
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    public EmployeeEntity() {
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }



}
