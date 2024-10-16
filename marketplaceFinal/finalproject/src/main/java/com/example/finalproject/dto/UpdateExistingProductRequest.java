package com.example.finalproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateExistingProductRequest {
    private String name;
    private String description;
    private String category;
    private Double price;
    private Integer quantity;
    private String sellerId;

}
