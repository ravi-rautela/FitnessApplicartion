package com.fitnessApplication.userservice.service;

import com.fitnessApplication.userservice.dto.UserResponseDTO;
import com.fitnessApplication.userservice.exception.ResourceNotFoundException;
import com.fitnessApplication.userservice.models.UserEntity;
import com.fitnessApplication.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserResponseDTO register(UserEntity request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exist.");
        }

        UserEntity savedUser = userRepository.save(request);

        // Traditional way to handle response
        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt()
        );

        // Using Model mapper
//        UserEntity savedUser = userRepository.save(request);
//        return modelMapper.map(savedUser, UserResponseDTO.class);

    }

    public UserResponseDTO getUserById(String id) {
        UserEntity user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with Id: " + id));

       return modelMapper.map(user, UserResponseDTO.class);

    }

    public List<UserResponseDTO> getAllUsers() {

        List<UserEntity> users = userRepository.findAll();
        return users
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .toList();
    }

    public Boolean existsByUserId(String id) {
        log.info("Calling User Service for {}", id);
        return userRepository.existsById(id);
    }
}
