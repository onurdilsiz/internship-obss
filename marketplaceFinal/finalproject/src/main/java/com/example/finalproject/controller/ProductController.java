package com.example.finalproject.controller;


import com.example.finalproject.dto.*;
import com.example.finalproject.service.ProductService;
import com.example.finalproject.service.SystemService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private SystemService systemService;
    @Qualifier("ProductServiceImpl")
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @PostMapping
    public BaseResponse<ProductDto> createNewProduct(@RequestBody @Valid CreateNewProductRequest createNewProductRequest){
        final ProductDto productDto = ProductDto.builder()
                .name(createNewProductRequest.getName())
                .description(createNewProductRequest.getDescription())
                .category(createNewProductRequest.getCategory())
                .price(createNewProductRequest.getPrice())
                .quantity(createNewProductRequest.getQuantity())
                .sellerId(createNewProductRequest.getSellerId())
                .build();

        return new BaseResponse<>( productService.createNewProduct(productDto));

    }


    @GetMapping("/{productId}")
    public BaseResponse<ProductDto> getProductDetail(@PathVariable("productId") String productId){
        return new BaseResponse<>(productService.findProductById(productId));
    }

    @Operation(summary = "Get products by parameters")
    @GetMapping()
    public BaseResponse<List<ProductDto>> searchProducts(@RequestParam(name = "search", required = false) String search,
                                                   @RequestParam(value = "size", required = false, defaultValue = "100") Integer size,
                                                   @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(value = "sort", required = false) String sort)
    {
        return new BaseResponse<>(productService.searchProducts(search,page,size,sort));

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @PutMapping("/{productId}")
    public BaseResponse<ProductDto> updateExistingProduct(@PathVariable("productId") String productId, @RequestBody @Valid UpdateExistingProductRequest updateExistingProductRequest){
         ProductDto productDto = ProductDto.builder()
                .name(updateExistingProductRequest.getName())
                .description(updateExistingProductRequest.getDescription())
                .category(updateExistingProductRequest.getCategory())
                .price(updateExistingProductRequest.getPrice())
                .quantity(updateExistingProductRequest.getQuantity())
                 .sellerId(updateExistingProductRequest.getSellerId())
                .build();

        return new BaseResponse<>( productService.updateExistingProduct(productId,productDto));

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable("productId") String productId){
        Objects.requireNonNull(productId, "user can not be null");
        systemService.deleteProduct(productId);
    }



}
