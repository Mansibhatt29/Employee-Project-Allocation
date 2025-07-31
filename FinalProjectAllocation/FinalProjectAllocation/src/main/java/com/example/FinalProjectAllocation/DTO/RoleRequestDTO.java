package com.example.FinalProjectAllocation.DTO;

import jakarta.transaction.UserTransaction;

public class RoleRequestDTO {

    private Long id;
    private String designation;
    private String status;

    private Long projectId;
    private String ProjectName;

    private Long managerEmpId;
    private String managerName;

    private Long selectedEmployeeId;
    private String selectedEmployeeName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public Long getManagerEmpId() {
        return managerEmpId;
    }

    public void setManagerEmpId(Long managerEmpId) {
        this.managerEmpId = managerEmpId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Long getSelectedEmployeeId() {
        return selectedEmployeeId;
    }

    public void setSelectedEmployeeId(Long selectedEmployeeId) {
        this.selectedEmployeeId = selectedEmployeeId;
    }

    public String getSelectedEmployeeName() {
        return selectedEmployeeName;
    }

    public void setSelectedEmployeeName(String selectedEmployeeName) {
        this.selectedEmployeeName = selectedEmployeeName;
    }
}
