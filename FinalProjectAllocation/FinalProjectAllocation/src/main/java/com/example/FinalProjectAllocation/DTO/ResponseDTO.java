package com.example.FinalProjectAllocation.DTO;


public class ResponseDTO {
    private String message;
    private String role;
    private Long empId;
    private String empName;
    private boolean needsPasswordUpdate;

    public boolean isNeedsPasswordUpdate() {
        return needsPasswordUpdate;
    }

    public void setNeedsPasswordUpdate(boolean needsPasswordUpdate) {
        this.needsPasswordUpdate = needsPasswordUpdate;
    }

    /*public ResponseDTO(String message, String role, Long empId, String empName, boolean needsPasswordUpdate) {
        this.message = message;
        this.role = role;
        this.empId = empId;
        this.empName = empName;
        this.needsPasswordUpdate = needsPasswordUpdate;
    }*/

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public ResponseDTO(String message, Long empId, String role, String empName) {
        this.message = message;
        this.empId = empId;
        this.role = role;
        this.empName = empName;
    }

    public ResponseDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }
}
