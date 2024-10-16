package com.example.finalproject.dto;


import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private Integer quantity;
    private String sellerId;
    private String sellerName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
