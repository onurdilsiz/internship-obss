package com.example.finalproject.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerDto {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private List<ProductDto> products;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
