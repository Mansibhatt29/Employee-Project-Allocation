package com.example.FinalProjectAllocation.controller;

import com.example.FinalProjectAllocation.DTO.ProjectDTO;
import com.example.FinalProjectAllocation.DTO.SkillUpdateDTO;
import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import com.example.FinalProjectAllocation.entities.ProjectEntity;
import com.example.FinalProjectAllocation.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity<EmployeeEntity> addEmployee(@RequestBody EmployeeEntity employeeEntity) {
        try {
            if (employeeEntity.getProjectEntity() == null || employeeEntity.getProjectEntity().getProjectId() == null) {
                employeeEntity.setProjectEntity(null);
            }

            EmployeeEntity saved = employeeService.addEmployee(employeeEntity);
            return ResponseEntity.ok(saved);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
//        if (employeeEntity.getProjectEntity() == null || employeeEntity.getProjectEntity().getProjectId() == null) {
////                employeeEntity.setProjectEntity(null);
//        }
//        employeeService.addEmployee(employeeEntity);
//        return ResponseEntity.ok(employeeEntity);
    }

    @GetMapping("/getSkillSetForEmployee/{empId}")
    public ResponseEntity<String> getSkillSet(@PathVariable Long empId){
        try {
            String skillSet = employeeService.getEmployeeSkillSet(empId);
            return ResponseEntity.ok(skillSet);
        } catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/updateSkillSet/{empId}")
    public ResponseEntity<String> updateSkillSet(@PathVariable Long empId, @RequestBody Map<String, String> body){
        String updatedSkillSet = body.get("skillSet");
        if(updatedSkillSet == null){
            return  ResponseEntity.badRequest().body("Skill set is missing in request body");
        }
        try {
            employeeService.updateSkillSet(empId, updatedSkillSet);
            return ResponseEntity.ok("Skill set updated successfully");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update skillset");
        }
    }

    @GetMapping("/getAllEmployees")
    public List<EmployeeEntity> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getProjectByEmployeeId/{empId}")
    public ProjectEntity getProjectByEmployeeId(@PathVariable Long empId){
        return employeeService.getAssignedProject(empId);
    }

//    @GetMapping("/getEmployeesWithoutProject")
//    public List<EmployeeEntity> getEmployeesWithoutProject() {
//        return employeeService.getEmployeesWithoutProject();
//    }

    @GetMapping("/getAvailableEmployees")
    public List<EmployeeEntity> getAvailableEmployeesByDesignation(@RequestParam String designation) {
        return employeeService.getAvailableEmployeesByDesignation(designation);
    }

    @GetMapping("/getAllEmployees/{designation}")
    public List<EmployeeEntity> getAllEmployeesOfADesignation(@PathVariable String designation) {
        return employeeService.getAllEmployeesOfADesignation(designation);
    }

    @GetMapping("/getAllAvailableEmployees")
    public List<EmployeeEntity> getAllAvailableEmployees() {
        return employeeService.getAllAvailableEmployees();
    }

    /*@PutMapping("updateEmployeeDetails/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeEntity updatedEmployee) {
        return employeeService.updateEmployee(id, updatedEmployee);
    }*/

    @PutMapping("/updateEmployeeDetails/{id}")
    public ResponseEntity<String> updateEmployeeDetails(@PathVariable Long id, @RequestBody EmployeeEntity updatedEmployee) {
        String result = employeeService.updateEmployeeDetails(id, updatedEmployee);

        if ("Employee updated successfully.".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body(result);
        }
    }


    @GetMapping("/getEmployeeById/{id}")
    public EmployeeEntity getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/deleteEmployeeById/{id}")
    public String deleteEmployeeById(@PathVariable Long id) {
        return employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/getMatchingProjectsForEmployee/{id}")
    public ResponseEntity<List<ProjectDTO>> getMatchingProjectsForEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getMatchingProjectsForEmployee(id));
    }



    private boolean hasMatchingSkills(String employeeSkills, String projectSkills) {
        String[] empSkillsArray = employeeSkills.split(",");

        for (String empSkill : empSkillsArray) {
            if (projectSkills.toLowerCase().contains(empSkill.trim().toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    /*@GetMapping("/getMatchingProjectsForEmployee/{id}")
    public List<ProjectEntity> matchProjects(@PathVariable Long id) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepo.findById(id);

        if (optionalEmployee.isPresent()) {
            EmployeeEntity employee = optionalEmployee.get();
            List<ProjectEntity> matchedProjects = new ArrayList<>();

            for (ProjectEntity project : projectRepo.findAll()) {
                if (hasMatchingSkills(employee.getSkillSet(), project.getRequiredSkills())) {
                    matchedProjects.add(project);
                }
            }
            return matchedProjects;
        }
        return Collections.emptyList();
    }*/







    /*@GetMapping("/getProjectByEmployeeId/{empId}")
    public ProjectEntity getProjectByEmployeeId(@PathVariable Long empId) {
        EmployeeEntity employee = employeeRepo.findByEmpId(empId);
        if (employee == null) {
            return null;
        }
        return projectRepo.findById(employee.getProjectId()).orElse(null);
    }


    @GetMapping("/getEmployeesByProjectId/{projectId}")
    public List<EmployeeEntity> getEmployeesByProjectId(@PathVariable Long projectId) {
        return employeeRepo.findByProjectId(projectId);
    }

     /*@GetMapping("/getProjectByEmpId/{empId}")
    public ProjectEntity getProjectByEmployeeId(@PathVariable Long empId) {
        EmployeeEntity employee = employeeRepo.findById(empId).orElse(null);
        return (employee != null) ? employee.getProjectEntity() : null;
    }*/


    /*@RequestMapping(value="/deleteEmployeeById/{id}", method = RequestMethod.DELETE)
    public String deleteEmployeeById(@PathVariable Long id){
        EmployeeEntity employeeEntity = employeeRepo.findById(id).get();
        if (employeeEntity != null) {
            employeeRepo.delete(employeeEntity);
            return "Employee deleted successfully";
        }
        else {
            return "Employee not found";
        }
        return null;
    }*/


    /*
    private List<Employee> employees = new ArrayList<>();

    @GetMapping(path = "/getEmployee/{id}")
    public employeeEntity getEmployeeById(@PathVariable int id) {
        for(int i = 0; i < employees.size(); i++) {
            employeeEntity emp = employees.get(i);
            if(emp.getId()==id){
                return emp;
            }
            //System.out.println(employees.get(i));
        }
        return null;
    }


    @GetMapping(path="/getEmployees")
    public List<employeeEntity> getAllEmployees(){
        return employees;
    }

    @PostMapping("/addEmployees")
    public String addEmployee(@RequestBody employeeEntity employee){
        employees.add(employee);
        return "Employee added successfully";
    }

    @RequestMapping(path = "/getAllEmployees", method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @RequestMapping(path = "/addEmployee", method = RequestMethod.POST)
    public String addEmployee(@RequestBody Employee emp) {
        employees.add(emp);
        return "Employee added successfully";
    }

    @RequestMapping(value="/deleteEmployee/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable int id){

    // boolean removed = employees.removeIf(emp -> emp.getId() == id);
    // return removed ? "Employee deleted successfully" : "Employee not found";

        for(int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            if (emp.getId() == id) {
                employees.remove(i);
                return "Employee deleted successfully";
            }
        }
        return "Employee not found";
    }

    @GetMapping(path = "/getEmployee")
    public Employee getEmployeeById(@RequestParam int id) {
        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    } */



}



