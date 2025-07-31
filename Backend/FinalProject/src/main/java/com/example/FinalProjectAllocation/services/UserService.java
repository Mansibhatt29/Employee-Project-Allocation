package com.example.FinalProjectAllocation.services;


import com.example.FinalProjectAllocation.DTO.LoginDTO;
import com.example.FinalProjectAllocation.DTO.ResponseDTO;
import com.example.FinalProjectAllocation.DTO.SignUpDTO;
import com.example.FinalProjectAllocation.DTO.UpdatePasswordRequest;
import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import com.example.FinalProjectAllocation.entities.ProjectEntity;
import com.example.FinalProjectAllocation.entities.UserDetails;
import com.example.FinalProjectAllocation.repositories.EmployeeRepo;
import com.example.FinalProjectAllocation.repositories.ProjectRepo;
import com.example.FinalProjectAllocation.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ProjectRepo projectRepo;

    public UserDetails saveUser(SignUpDTO signUpDTO) {

        if (userRepo.findByUsername(signUpDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmpName(signUpDTO.getName());
        employee.setDesignation(signUpDTO.getDesignation());
        employee.setAvailability(signUpDTO.getAvailability());
        employee.setSkillSet(signUpDTO.getSkillSet());

        if (signUpDTO.getProjectId() != null) {
            Optional<ProjectEntity> project = projectRepo.findById(signUpDTO.getProjectId());
            project.ifPresent(employee::setProjectEntity);
        }
        System.out.println("EmployeeEntity coming from Signup"+ employee.toString());
        EmployeeEntity savedEmployee = employeeRepo.save(employee);

        UserDetails user = new UserDetails();
        user.setUsername(signUpDTO.getUsername());
        user.setPassword(signUpDTO.getPassword());
        user.setRole(signUpDTO.getRole());
        user.setEmployee(savedEmployee);

        return userRepo.save(user);
    }


    public ResponseDTO login(LoginDTO loginDTO) {
        Optional<UserDetails> userOpt = userRepo.findByUsername(loginDTO.getUsername());

        if (userOpt.isPresent()) {
            UserDetails user = userOpt.get();

//            System.out.println("fetched User , " + user);
//            System.out.println("Employee Linked to user: " + user.getEmployee());
//            System.out.println("Employee name: " + user.getEmployee().getEmpName());

            if (user.getPassword().equals(loginDTO.getPassword())) {
                Long empId = user.getEmployee().getEmpId();
                String empName = user.getEmployee().getEmpName();

                ResponseDTO responseDTO = new ResponseDTO("Login successful",empId, user.getRole(), empName );
                boolean usingTemp = loginDTO.getPassword().equals("Temp123");
                responseDTO.setNeedsPasswordUpdate(usingTemp);

                return responseDTO;
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("User Not found");
        }
    }

    public String updatePassword(UpdatePasswordRequest request){
        Optional <UserDetails> optionalUser = userRepo.findByUsername(request.getUsername());

        if(optionalUser.isPresent()){
            UserDetails user = optionalUser.get();
            user.setPassword(request.getNewPassword());
            userRepo.save(user);
            return  "Password updated successfully!";
        } else {
            return "User not found!";
        }
    }


}
