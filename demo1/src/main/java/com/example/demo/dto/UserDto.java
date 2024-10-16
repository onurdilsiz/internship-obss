package com.example.demo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;
    private String name;
    private String email;
    private String surname;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
