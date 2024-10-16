package com.example.finalproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateExistingUserRequest {
    @Size(min = 2, max = 50)
    private String name;
    @Email
    private String email;

    @Size(min = 2, max = 50)
    private String username;
    @Size(min = 2, max = 50)
    private String surname;



}
