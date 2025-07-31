package com.example.FinalProjectAllocation.DTO;

public class SkillUpdateDTO {

    private Long empId;
    private String updatedSkillSet;


    public String getUpdatedSkillSet() {
        return updatedSkillSet;
    }

    public void setUpdatedSkillSet(String updatedSkillSet) {
        this.updatedSkillSet = updatedSkillSet;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }



}
