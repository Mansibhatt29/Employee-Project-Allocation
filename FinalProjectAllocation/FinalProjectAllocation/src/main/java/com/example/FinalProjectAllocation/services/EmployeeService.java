package com.example.FinalProjectAllocation.services;

import com.example.FinalProjectAllocation.DTO.ProjectDTO;
import com.example.FinalProjectAllocation.DTO.SkillUpdateDTO;
import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import com.example.FinalProjectAllocation.entities.ProjectEntity;
import com.example.FinalProjectAllocation.entities.UserDetails;
import com.example.FinalProjectAllocation.repositories.EmployeeRepo;
import com.example.FinalProjectAllocation.repositories.ProjectRepo;
import com.example.FinalProjectAllocation.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JavaMailSender mailSender;

    public void sendCredentialsEmail(String toEmail, String username, String password, String empName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Your Login Credentials");
            message.setText("Hi " + empName + ",\n\n" + "Your account has been created successfully.\n\n"
                    + "Username: " + username + "\n"
                    + "Password: " + password + "\n\n"
                    + "Please log in to the employee portal and update your password.\n\n" + "Regards, \nAdmin Team");
            mailSender.send(message);
            System.out.println("Email successfully sent to: " + toEmail);
        } catch (Exception e) {
            System.err.println("Failed to send email to:" + toEmail);
        }
    }

    
    public EmployeeEntity addEmployee(EmployeeEntity employeeEntity) {
        System.out.println("EmployeeEntity in add employee for admin"+ employeeEntity);
        if (employeeEntity.getProjectEntity() != null && employeeEntity.getProjectEntity().getProjectId() != null){
            Long projectId = employeeEntity.getProjectEntity().getProjectId();
            ProjectEntity projectEntity = projectRepo.findById(projectId).orElse(null);
            employeeEntity.setProjectEntity(projectEntity);
        }
        EmployeeEntity savedEmployee = employeeRepo.save(employeeEntity);

        UserDetails user = new UserDetails();
        user.setUsername(savedEmployee.getEmpName().toLowerCase().replaceAll("\\s","") + savedEmployee.getEmpId());
        user.setPassword("temp123");
        user.setRole(employeeEntity.getRole());
        user.setEmployee(savedEmployee);

        userRepo.save(user);

        try{
            sendCredentialsEmail(employeeEntity.getEmail(), user.getUsername(), user.getPassword(), employeeEntity.getEmpName());
        } catch (Exception e) {
            System.out.println("Failed to send email to : " + savedEmployee.getEmail());
            //e.printStackTrace();
        }
        return savedEmployee;
    }

    public String getEmployeeSkillSet(Long empId){
        Optional<EmployeeEntity> employeeOpt = employeeRepo.findById(empId);
        if(employeeOpt.isPresent()){
            return employeeOpt.get().getSkillSet();
        } else{
            throw new RuntimeException("Employee not found with ID: " + empId);
        }
    }

    public void updateSkillSet(Long empId, String updatedSkillSet){
        Optional<EmployeeEntity> employeeOpt = employeeRepo.findById(empId);
        if( employeeOpt.isPresent()){
            EmployeeEntity employeeEntity = employeeOpt.get();
            employeeEntity.setSkillSet(updatedSkillSet);
            employeeRepo.save(employeeEntity);
        } else {
            throw new RuntimeException("Employee not found with Id: " + empId) ;
        }
    }


    public List<EmployeeEntity> getAllEmployees() {
        System.out.println("inside get");
        return employeeRepo.findAll();
    }

    public ProjectEntity getAssignedProject(Long empId){
        EmployeeEntity employeeEntity = employeeRepo.findByEmpId(empId);
        if(employeeEntity != null){
            return employeeEntity.getProjectEntity();
        }
        return null;
    }

    public String updateEmployeeDetails(Long empId, EmployeeEntity updatedEmployee) {
        EmployeeEntity existingEmployee = employeeRepo.findByEmpId(empId);

        if (existingEmployee != null) {
            existingEmployee.setEmpName(updatedEmployee.getEmpName());
            existingEmployee.setDesignation(updatedEmployee.getDesignation());
            existingEmployee.setAvailability(updatedEmployee.isAvailability());
            existingEmployee.setSkillSet(updatedEmployee.getSkillSet());

            ProjectEntity updatedProject = updatedEmployee.getProjectEntity();

            if(updatedProject != null && updatedProject.getProjectId() != null){
                ProjectEntity projectFromDb = projectRepo.findById(updatedProject.getProjectId()).orElseThrow(() -> new IllegalArgumentException("Invalid Project ID"));
                existingEmployee.setProjectEntity(projectFromDb);
            } else {
                existingEmployee.setProjectEntity(null);
            }
//            if(updatedEmployee.getProjectEntity() != null){
//                existingEmployee.setProjectEntity(updatedEmployee.getProjectEntity());
//            }
            //existingEmployee.setProjectEntity(updatedEmployee.getProjectEntity());

            employeeRepo.save(existingEmployee);
            return "Employee updated successfully.";
        } else {
            return "Employee not found.";
        }
    }

//    public List<EmployeeEntity> getEmployeesWithoutProject() {
//
//        return employeeRepo.findByProjectEntityIsNull();
//    }

    public List<EmployeeEntity> getAvailableEmployeesByDesignation(String designation) {
        return employeeRepo.findAvailableEmployeesByDesignation(designation);
    }

    public List<EmployeeEntity> getAllEmployeesOfADesignation(String designation) {
        return employeeRepo.findByDesignation(designation);
    }

    public List<EmployeeEntity> getAllAvailableEmployees() {

        return employeeRepo.findByAvailabilityTrue();
    }

    public EmployeeEntity getEmployeeById(Long id) {
        return employeeRepo.findById(id).orElse(null);
    }

    public String deleteEmployeeById(Long id) {
        EmployeeEntity employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        UserDetails userDetails = userRepo.findByEmployee(employee);
        if(userDetails != null){
            userRepo.delete(userDetails);
        }
        employeeRepo.deleteById(id);
        return "Employee with id " + id + " has been deleted.";
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

    public List<ProjectDTO> getMatchingProjectsForEmployee(Long id) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepo.findById(id);

        if (optionalEmployee.isPresent()) {
            EmployeeEntity employee = optionalEmployee.get();
            List<ProjectDTO> matchedProjects = new ArrayList<>();

            for (ProjectEntity project : projectRepo.findAll()) {
                double matchPercentage = calculateSkillMatchPercentage(employee.getSkillSet(), project.getRequiredSkills());

                if (matchPercentage > 0) {
                    ProjectDTO projectDTO = new ProjectDTO(
                            project.getProjectId(),
                            project.getProjectName(),
                            project.getDescript(),
                            project.getCapacity(),
                            project.getRequiredSkills(),
                            matchPercentage
                    );
                    matchedProjects.add(projectDTO);
                }
            }
            return matchedProjects;
        }
        return Collections.emptyList();
    }


    public int calculateSkillMatchScore(String employeeSkills, String projectSkills) {
        String[] empSkillsArray = employeeSkills.split(",");
        int score = 0;
        for (String empSkill : empSkillsArray) {
            if (projectSkills.toLowerCase().contains(empSkill.trim().toLowerCase())) {
                score += 10;
            }
        }
        return score;
    }

    /*public List<ProjectDTO> getMatchingProjectsForEmployee(Long id) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepo.findById(id);

        if (optionalEmployee.isPresent()) {
            EmployeeEntity employee = optionalEmployee.get();
            List<ProjectDTO> matchedProjects = new ArrayList<>();

            for (ProjectEntity project : projectRepo.findAll()) {
                int score = calculateSkillMatchScore(employee.getSkillSet(), project.getRequiredSkills());

                if (score > 0) {
                    matchedProjects.add(new ProjectDTO(
                            project.getProjectId(), project.getProjectName(), project.getRequiredSkills(), score));
                }
            }
            return matchedProjects;
        }
        return Collections.emptyList();
    }*/




}

