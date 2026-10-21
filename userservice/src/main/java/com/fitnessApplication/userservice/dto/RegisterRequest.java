package com.fitnessApplication.userservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
