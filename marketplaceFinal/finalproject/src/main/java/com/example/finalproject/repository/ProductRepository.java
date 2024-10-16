package com.example.finalproject.repository;

import com.example.finalproject.model.Product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository  extends JpaRepository<Product, UUID> {

    List<Product> findByNameContaining(String name, Pageable pageable);
    Optional<Product> findProductById(UUID  productId);

    @Query("from Product")
    List<Product> findAllProducts(Pageable pageable);


//    List<Product> findProductsBySeller(User seller);
//
//    List<Product> findProductsBySellerAndNameContaining(User seller, String name);
//
//    List<Product> findProductsBySellerAndCategory(User seller, String category);


}
