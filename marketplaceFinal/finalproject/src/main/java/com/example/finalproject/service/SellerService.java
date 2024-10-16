package com.example.finalproject.service;

import com.example.finalproject.dto.CreateNewSellerRequest;
import com.example.finalproject.dto.SellerDto;
import com.example.finalproject.dto.UpdateExistingSellerRequest;
import com.example.finalproject.model.Seller;

import java.util.List;
import java.util.UUID;

public interface SellerService {
    SellerDto updateExistingSeller(String sellerId, SellerDto sellerDto);
    void deleteSeller(String id);
    SellerDto findSellerById(String sellerId);
    Seller findSellerObjectById(String sellerId);
    List<UUID> getUsersBlacklisted(String sellerId) ;

    List<UUID> getProductsBySeller(String sellerId) ;


    List<SellerDto> searchSellers(String search, Integer page, Integer size, String sort);
    SellerDto createNewSeller(SellerDto sellerDto);
    List<SellerDto> generateSampleSellers(int targetSellerSize);
}
