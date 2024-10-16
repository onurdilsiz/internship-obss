package com.example.finalproject.service;

import com.example.finalproject.dto.ProductDto;
import com.example.finalproject.dto.SellerDto;
import com.example.finalproject.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductDto createNewProduct(ProductDto productDto);

    List<ProductDto> searchProducts(String search, Integer page, Integer size, String sort);
//
    ProductDto updateExistingProduct(String productId, ProductDto productDto);
//
    List<UUID> getUsersWhoFavorited(String productId);

    void deleteProduct(String productId);
//
    ProductDto findProductById(String productId);

    void generateSampleProducts(int targetProductSize, List<SellerDto>sellerIds) ;

}
