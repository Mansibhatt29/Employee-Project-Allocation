package com.example.FinalProjectAllocation.services;


import com.example.FinalProjectAllocation.DTO.EmployeeMatchDTO;
import com.example.FinalProjectAllocation.DTO.RoleRequestDTO;
import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import com.example.FinalProjectAllocation.entities.ProjectEntity;
import com.example.FinalProjectAllocation.entities.RoleRequestEntity;
import com.example.FinalProjectAllocation.repositories.EmployeeRepo;
import com.example.FinalProjectAllocation.repositories.ProjectRepo;
import com.example.FinalProjectAllocation.repositories.RoleRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleRequestService {

    @Autowired
    private RoleRequestRepo roleRequestRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private EmployeeRepo employeeRepo;


    public RoleRequestEntity saveRoleRequest(RoleRequestEntity request){
        if(request.getProject() != null && request.getProject().getProjectId() != null){
            Long projectId = request.getProject().getProjectId();
            ProjectEntity project = projectRepo.findById(projectId).orElseThrow(() -> new RuntimeException("Project with ID " + projectId + "not found."));
            request.setProject(project);
        } else {
            throw new RuntimeException("Project information is missing in the request.");
        }
        if(request.getManager() != null && request.getManager().getEmpId() != null){
            EmployeeEntity manager = employeeRepo.findById(request.getManager().getEmpId()).orElseThrow(() -> new RuntimeException("Manager not found"));
            request.setManager(manager);
        } else{
            throw new RuntimeException("Manager info is missing");
        }
        request.setStatus("Pending");
        return roleRequestRepo.save(request);
    }

    public List<RoleRequestDTO> getPendingRequests()  {
        List<RoleRequestEntity> requests = roleRequestRepo.findByStatus("Pending");
        List<RoleRequestDTO> dtos = new ArrayList<>();

        for(RoleRequestEntity request : requests){
            dtos.add(convertToDTO(request));
        }
        return  dtos;
    }
    private RoleRequestDTO convertToDTO(RoleRequestEntity entity){
        RoleRequestDTO dto = new RoleRequestDTO();

        dto.setId(entity.getId());
        dto.setDesignation(entity.getDesignation());
        dto.setStatus(entity.getStatus());

        if(entity.getManager() != null){
            dto.setManagerEmpId(entity.getManager().getEmpId());
            dto.setManagerName(entity.getManager().getEmpName());
        }
        if(entity.getProject() != null){
            dto.setProjectId(entity.getProject().getProjectId());
            dto.setProjectName(entity.getProject().getProjectName());
        }

        return dto;
    }

    public double calculateSkillMatchPercentage(String employeeSkills, String projectSkills) {
        String[] empSkillsArray = employeeSkills.split(",");
        String[] projectSkillsArray = projectSkills.split(",");

        int matchedSkillsCount = 0;
        int totalProjectSkillsCount = projectSkillsArray.length;

        for (String projectSkill : projectSkillsArray) {
            for (String empSkill : empSkillsArray) {
                if (projectSkill.trim().equalsIgnoreCase(empSkill.trim())) {
                    matchedSkillsCount++;
                    break;
                }
            }
        }
        return totalProjectSkillsCount > 0 ? (matchedSkillsCount / (double) totalProjectSkillsCount) * 100 : 0.0;
    }
    public List<EmployeeMatchDTO> getEligibleEmployees(Long id){
        RoleRequestEntity request = roleRequestRepo.findById(id).orElseThrow(() -> new RuntimeException("Role request not found"));
        String requestedDesignation = request.getDesignation();
        ProjectEntity project = request.getProject();

        if(project ==  null){
            throw new RuntimeException("Project is not assigned in the role request.");
        }
        String projectSkills = project.getRequiredSkills();
        List<EmployeeEntity> employees = employeeRepo.findAvailableEmployeesByDesignation(requestedDesignation);
        List<EmployeeMatchDTO> matches = new ArrayList<>();
        for (EmployeeEntity emp: employees){
            double matchPercentage = calculateSkillMatchPercentage(emp.getSkillSet(), projectSkills);
            EmployeeMatchDTO dto = new EmployeeMatchDTO();
            dto.setEmpId(emp.getEmpId());
            dto.setEmpName(emp.getEmpName());
            dto.setDesignation(emp.getDesignation());
            dto.setSkillSet(emp.getSkillSet());
            dto.setMatchPercentage(matchPercentage);
            dto.setProjectId(project.getProjectId());
            dto.setProjectName(project.getProjectName());
            matches.add(dto);
        }
        return matches;
    }

    public void assignEmployeeToProject(Long id, Long empId){
        RoleRequestEntity request = roleRequestRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid request Id"));
        EmployeeEntity employee = employeeRepo.findById(empId).orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));
        ProjectEntity project = request.getProject();
        if(project == null){
            throw new IllegalArgumentException("No project found for this role request.");
        }
        employee.setProjectEntity(project);
        employee.setAvailability(false);
        employeeRepo.save(employee);

        request.setSelectedEmployee(employee);

        request.setStatus("Approved");
        roleRequestRepo.save(request);
    }

    public List<RoleRequestDTO> getAllRequestsByManager(Long managerEmpId){
        List<RoleRequestEntity> requestEntities = roleRequestRepo.findByManagerEmpId(managerEmpId);
        List<RoleRequestDTO> dtoList = new ArrayList<>();

        for(RoleRequestEntity request: requestEntities){
            RoleRequestDTO dto = new RoleRequestDTO();
            dto.setId(request.getId());
            dto.setProjectId(request.getProject().getProjectId());
            dto.setProjectName(request.getProject().getProjectName());
            dto.setDesignation(request.getDesignation());
            dto.setStatus(request.getStatus());
            dto.setManagerEmpId(request.getManager().getEmpId());

            if(request.getSelectedEmployee() != null){
                dto.setSelectedEmployeeId(request.getSelectedEmployee().getEmpId());
                dto.setSelectedEmployeeName(request.getSelectedEmployee().getEmpName());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }
}
