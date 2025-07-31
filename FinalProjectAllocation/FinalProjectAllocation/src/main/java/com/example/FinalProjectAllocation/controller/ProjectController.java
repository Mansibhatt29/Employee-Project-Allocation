package com.example.FinalProjectAllocation.controller;

import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import com.example.FinalProjectAllocation.entities.ProjectEntity;
import com.example.FinalProjectAllocation.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/getAllProjectIds")
    public List<Long> getAllProjectIds(){
        return projectService.getAllProjectIds();
    }


    @PostMapping("/addProject")
    public ProjectEntity addProject(@RequestBody ProjectEntity projectEntity) {
        return projectService.addProject(projectEntity);
    }

    @GetMapping("/getAllProjects")
    public List<ProjectEntity> getAllProjects() {
        return projectService.getAllProjects();
    }

    @RequestMapping(value = "/getProjectById/{projectId}", method = RequestMethod.GET)
    public ProjectEntity getProjectById(@PathVariable Long projectId) {
        return projectService.getProjectById(projectId);
    }

    @PutMapping("/updateProjectDetails/{id}")
    public ResponseEntity<String> updateProjectDetails(@PathVariable Long id, @RequestBody ProjectEntity updatedProject){
        String result = projectService.updateProjectDetails(id, updatedProject);
        if("Project updated successfully".equals(result)){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @GetMapping("/getEmployeesInProject/{projectId}")
    public List<EmployeeEntity> getEmployeesInProject(@PathVariable Long projectId) {
        return projectService.getEmployeesInProject(projectId);
    }

    @GetMapping("/getProjectsWithoutEmployees")
    public List<ProjectEntity> getProjectsWithoutEmployees() {
        return projectService.getProjectsWithoutEmployees();
    }

    @GetMapping("/getUnderStaffedProjects")
    public List<ProjectEntity> getUnderstaffedProjects() {
        return projectService.getUnderstaffedProjects();
    }



    /*@PutMapping("/updateProject/{projectId}")
    public ResponseEntity<ProjectEntity> updateProject(
            @PathVariable Long projectId,
            @RequestBody ProjectEntity updatedProject) {

        ProjectEntity savedProject = projectService.updateProject(projectId, updatedProject);
        return (savedProject != null) ? ResponseEntity.ok(savedProject) : ResponseEntity.notFound().build();
    }*/

    /*
    @PostMapping("/addProject")
    public ProjectEntity addProject(@RequestBody ProjectEntity projectEntity) {
        return projectRepo.save(projectEntity);
    }

    @GetMapping("/getAllProjects")
    public List<ProjectEntity> getAllProjects() {
        return projectRepo.findAll();
    }

    @RequestMapping(value = "/getProjectById/{id}", method = RequestMethod.GET)
    public ProjectEntity getProjectById(@PathVariable Long id){
        return projectRepo.findById(id).get();
    }

    @GetMapping("/getEmployeesInProject/{projectId}")
    public List<EmployeeEntity> getEmployeesByProject(@PathVariable Long projectId) {
        ProjectEntity projectEntity = projectRepo.findById(projectId).orElse(null);
        return (projectEntity != null) ? projectEntity.getEmployees() : null;
    }

    @GetMapping("/getProjectsWithoutEmployees")
    public List<ProjectEntity> getProjectsWithoutEmployees() {
        return projectRepo.findByEmployeesIsEmpty();
    }

    @GetMapping("/getUnderStaffedProjects")
    public List<ProjectEntity> getUnderstaffedProjects() {
        return projectRepo.findUnderstaffedProjects();
    }

    @PutMapping("/updateProject/{projectId}")
    public ResponseEntity<ProjectEntity> updateProject(
            @PathVariable Long projectId,
            @RequestBody ProjectEntity updatedProject) {

        return projectRepo.findById(projectId)
                .map(project -> {
                    project.setProjectName(updatedProject.getProjectName());
                    project.setDescript(updatedProject.getDescript());
                    project.setCapacity(updatedProject.getCapacity());

                    ProjectEntity savedProject = projectRepo.save(project);
                    return ResponseEntity.ok(savedProject);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }*/

}
