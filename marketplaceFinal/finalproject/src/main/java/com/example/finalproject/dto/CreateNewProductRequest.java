package com.example.finalproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewProductRequest {
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    private String description;
    @NotBlank
    @Size(min = 2, max = 50)
    private String category;
    @NotNull
    private double price;
    @NotNull
    private int quantity;

    @NotBlank
    private String sellerId;




}
