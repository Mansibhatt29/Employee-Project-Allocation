package com.example.FinalProjectAllocation.controller;


import com.example.FinalProjectAllocation.DTO.EmployeeMatchDTO;
import com.example.FinalProjectAllocation.DTO.ResponseDTO;
import com.example.FinalProjectAllocation.DTO.RoleRequestDTO;
import com.example.FinalProjectAllocation.entities.RoleRequestEntity;
import com.example.FinalProjectAllocation.repositories.RoleRequestRepo;
import com.example.FinalProjectAllocation.services.RoleRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleRequestController {

    @Autowired
    private RoleRequestService roleRequestService;

    @Autowired
    private RoleRequestRepo roleRequestRepo;

    @PostMapping("/raiseRequest")
    public ResponseEntity<RoleRequestEntity> raiseRequest(@RequestBody RoleRequestEntity request){
        RoleRequestEntity savedRequest = roleRequestService.saveRoleRequest(request);
        return ResponseEntity.ok(savedRequest);
    }

    @GetMapping("/getAllPendingRequests")
    public ResponseEntity<List<RoleRequestDTO>> getAllPendingRequests() {
        List<RoleRequestDTO> pendingRequests = roleRequestService.getPendingRequests();
        return ResponseEntity.ok(pendingRequests);
    }

    @GetMapping ("/getPendingRequestCount")
    public ResponseEntity<Long> getPendingRequestCount(){
        Long count = roleRequestRepo.countByStatus("Pending");
        return ResponseEntity.ok(count);
    }

    @GetMapping("/getEligibleEmployees/{id}")
    public ResponseEntity<List<EmployeeMatchDTO>> getEligibleEmployees(@PathVariable Long id){
        List<EmployeeMatchDTO> eligibleEmployees = roleRequestService.getEligibleEmployees(id);
        return ResponseEntity.ok(eligibleEmployees);
    }

    @PutMapping("/assignEmployee")
    public ResponseEntity<String> assignEmployeeToProject(@RequestParam Long id, @RequestParam Long empId){
        try{
            roleRequestService.assignEmployeeToProject(id, empId);
            return ResponseEntity.ok("Employee assigned and status updated successfully.");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Assignment failed: " + e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("/getRequestsByManager")
    public ResponseEntity<List<RoleRequestDTO>> getRequestsByManager(@RequestParam Long managerEmpId){
        List<RoleRequestDTO> requests = roleRequestService.getAllRequestsByManager(managerEmpId);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
}
