package com.example.FinalProjectAllocation.DTO;

public class EmployeeDTO {

    private int id;
    private String empName;
    private String designation;
    private boolean availability;
    private String skillSet;

    public EmployeeDTO(int id, String empName, String designation, boolean availability, String skillSet) {
        this.id = id;
        this.empName = empName;
        this.designation = designation;
        this.availability = availability;
        this.skillSet = skillSet;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", designation='" + designation + '\'' +
                ", availability=" + availability +
                ", skillSet='" + skillSet + '\'' +
                '}';
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


}
