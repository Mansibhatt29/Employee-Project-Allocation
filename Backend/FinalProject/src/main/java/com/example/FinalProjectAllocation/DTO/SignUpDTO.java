package com.example.FinalProjectAllocation.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignUpDTO {

    private String username;
    private String password;
    private String role;
    private String name;
    private String designation;
    //@JsonProperty("availability")
    private Boolean availability;
    private Long projectId;
    //private Long empId;
    private String skillSet;

    /*public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }*/

    public SignUpDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


    public Long getProjectId() {
        return projectId;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }
}
