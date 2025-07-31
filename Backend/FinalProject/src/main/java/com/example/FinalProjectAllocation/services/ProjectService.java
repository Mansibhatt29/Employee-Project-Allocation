package com.example.FinalProjectAllocation.services;


import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import com.example.FinalProjectAllocation.entities.ProjectEntity;
import com.example.FinalProjectAllocation.repositories.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    public List<Long> getAllProjectIds(){
        List<ProjectEntity> projects = projectRepo.findAll();
        List<Long> projectIds = new ArrayList<>();

        for(ProjectEntity projectEntity : projects){
            projectIds.add(projectEntity.getProjectId());
        }

        return projectIds;
    }

    public ProjectEntity addProject(ProjectEntity projectEntity) {
        return projectRepo.save(projectEntity);
    }

    public List<ProjectEntity> getAllProjects() {
        return projectRepo.findAll();
    }

    public String updateProjectDetails(Long projectId, ProjectEntity updatedProject){
        ProjectEntity existing = projectRepo.findById(projectId).orElse(null);
        if(existing == null){
            return "Project not found";
        }

        existing.setProjectName(updatedProject.getProjectName());
        existing.setDescript(updatedProject.getDescript());
        existing.setCapacity(updatedProject.getCapacity());
        existing.setRequiredSkills(updatedProject.getRequiredSkills());

        projectRepo.save(existing);
        return "Project updated successfully";
    }

    public ProjectEntity getProjectById(Long projectId) {
        return projectRepo.findById(projectId).orElse(null);
    }

    public List<EmployeeEntity> getEmployeesInProject(Long projectId) {
        ProjectEntity projectEntity = projectRepo.findById(projectId).orElse(null);
        return (projectEntity != null) ? projectEntity.getEmployees() : null;
    }

    public List<ProjectEntity> getProjectsWithoutEmployees() {
        return projectRepo.findByEmployeesIsEmpty();
    }

    public List<ProjectEntity> getUnderstaffedProjects() {
        return projectRepo.findUnderstaffedProjects();
    }

    /*public ProjectEntity updateProject(Long projectId, ProjectEntity updatedProject) {
        Optional<ProjectEntity> optionalProject = projectRepo.findById(projectId);

        if (optionalProject.isPresent()) {
            ProjectEntity existingProject = optionalProject.get();

            existingProject.setProjectName(updatedProject.getProjectName());
            existingProject.setDescript(updatedProject.getDescript());
            existingProject.setCapacity(updatedProject.getCapacity());

            return projectRepo.save(existingProject);
        } else {
            return null;
        }
    }*/
}
       /*return projectRepo.findById(projectId)
                .map(project -> {
                    project.setProjectName(updatedProject.getProjectName());
                    project.setDescript(updatedProject.getDescript());
                    project.setCapacity(updatedProject.getCapacity());
                    return projectRepo.save(project);
                })
                .orElse(null);*/