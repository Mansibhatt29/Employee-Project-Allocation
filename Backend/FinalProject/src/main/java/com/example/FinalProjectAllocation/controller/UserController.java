package com.example.FinalProjectAllocation.controller;

import com.example.FinalProjectAllocation.DTO.ResponseDTO;
import com.example.FinalProjectAllocation.DTO.LoginDTO;
import com.example.FinalProjectAllocation.DTO.SignUpDTO;
import com.example.FinalProjectAllocation.DTO.UpdatePasswordRequest;
import com.example.FinalProjectAllocation.entities.UserDetails;
import com.example.FinalProjectAllocation.repositories.UserRepo;
import com.example.FinalProjectAllocation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpDTO signUpDTO) {
        try {
            UserDetails user = userService.saveUser(signUpDTO);
            System.out.println("Received DTO: + signupDTO");
            return ResponseEntity.ok("User registered successfully: " + user.getUsername());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Signup failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        try{
            ResponseDTO response = userService.login(loginDTO);
            return ResponseEntity.ok(response);
        } catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO(e.getMessage(),null, null, null));
        }
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<ResponseDTO> updatePassword(@RequestBody UpdatePasswordRequest request){
        String result = userService.updatePassword(request);
        ResponseDTO response = new ResponseDTO();
        response.setMessage(result);
        if("Password updated successfully!".equals(result)) {
            return ResponseEntity.ok(response);
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    /*
    @PostMapping("/addUser")
    public ResponseEntity<UserDetails> signUp(@RequestBody SignUpDTO signUpDTO) {
        UserDetails savedUser = userService.saveUser(signUpDTO);
        return ResponseEntity.ok(savedUser);
    }*/


}
