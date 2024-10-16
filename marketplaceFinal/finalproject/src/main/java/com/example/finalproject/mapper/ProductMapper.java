package com.example.finalproject.mapper;

import com.example.finalproject.dto.ProductDto;
import com.example.finalproject.model.Product;
import com.example.finalproject.utils.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ProductMapper {

        public Product toModel(ProductDto dto){
            Product product = new Product();
            if (StringUtils.isNotBlank(dto.getId())) {
                product.setId(UUID.fromString(dto.getId()));
            }
            product.setName(dto.getName());
            product.setCategory(dto.getCategory());
            product.setPrice(dto.getPrice());
            product.setDescription(dto.getDescription());
            product.setQuantity(dto.getQuantity());


            return product;

        }
        public ProductDto toDto(Product model) {
            ProductDto dto = new ProductDto();
            dto.setName(model.getName());
            dto.setCategory(model.getCategory());
            dto.setPrice(model.getPrice());
            dto.setDescription(model.getDescription());
            dto.setQuantity(model.getQuantity());
            dto.setId(model.getId().toString());
            dto.setSellerId(model.getSeller().getId().toString());
            dto.setSellerName(model.getSeller().getName());

            dto.setCreatedAt(model.getCreatedAt());
            dto.setUpdatedAt(model.getUpdatedAt());

            return dto;
        }



}
