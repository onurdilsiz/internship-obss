package com.example.finalproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewUserRequest {
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 2, max = 50)
    private String surname;

    private String username;
    private String password;
}
