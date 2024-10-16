package com.example.finalproject.mapper;

import com.example.finalproject.dto.SellerDto;
import com.example.finalproject.model.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SellerMapper  {
    private final ProductMapper productMapper;




    public Seller toModel(SellerDto dto){
        Seller seller = new Seller();
        seller.setName(dto.getName());
        seller.setAddress(dto.getAddress());
        seller.setPhone(dto.getPhone());
        seller.setEmail(dto.getEmail());

        seller.setCreatedAt(dto.getCreatedAt());
        seller.setUpdatedAt(dto.getUpdatedAt());
        return seller;

    }

    public SellerDto toDto(Seller model) {
        SellerDto dto = new SellerDto();
        dto.setName(model.getName());
        dto.setAddress(model.getAddress());
        dto.setPhone(model.getPhone());
        dto.setEmail(model.getEmail());
        dto.setId(model.getId().toString());
        dto.setProducts(model.getProducts().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList()));
        dto.setCreatedAt(model.getCreatedAt());
        dto.setUpdatedAt(model.getUpdatedAt());
        return dto;
    }

}
