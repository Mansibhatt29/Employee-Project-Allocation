package com.example.FinalProjectAllocation.DTO;

public class ProjectDTO {
    private Long projectId;
    private String projectName;
    private String descript;
    private int capacity;
    private String requiredSkills;
    private double matchPercentage;

    public ProjectDTO(Long projectId, String projectName, String descript, int capacity, String requiredSkills, double matchPercentage) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.descript = descript;
        this.capacity = capacity;
        this.requiredSkills = requiredSkills;
        this.matchPercentage = matchPercentage;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(int matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }


}
