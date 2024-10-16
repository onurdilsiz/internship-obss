package com.example.finalproject.repository;

import com.example.finalproject.model.Seller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SellerRepository extends JpaRepository<Seller, UUID> {
    Optional<Seller> findSellerById(UUID id);
    List<Seller> findSellersByNameContainingIgnoreCase(String name, Pageable pageable);
    @Query("from Seller")
    List<Seller> findAllSellers(Pageable pageable);


}
