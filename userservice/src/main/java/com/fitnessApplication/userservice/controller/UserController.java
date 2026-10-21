package com.fitnessApplication.userservice.controller;


import com.fitnessApplication.userservice.dto.RegisterRequest;
import com.fitnessApplication.userservice.dto.UserResponseDTO;
import com.fitnessApplication.userservice.models.UserEntity;
import com.fitnessApplication.userservice.service.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public List<UserResponseDTO> getAllUser(){
        return userServices.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id){
        return ResponseEntity.ok(userServices.getUserById(id));
    }

    @GetMapping("/{id}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable String id){
        return ResponseEntity.ok(userServices.existsByUserId(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserEntity request){
        return ResponseEntity.ok(userServices.register(request));
    }

}
