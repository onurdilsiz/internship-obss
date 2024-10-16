package com.example.finalproject.service.impl;


import com.example.finalproject.service.ProductService;
import com.example.finalproject.service.SellerService;
import com.example.finalproject.service.SystemService;
import com.example.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor

public class SystemServiceImpl implements SystemService {
    private final ProductService productService;
    private final UserService userService;
    private final SellerService sellerService;


        @Override
        public void deleteProduct(String productId) {
            List<UUID> userIds = productService.getUsersWhoFavorited(productId);
            userService.deleteFavoriteProducts(userIds, productId);
            productService.deleteProduct(productId);
        }
        @Override
        public void deleteSeller(String sellerId) {
            List<UUID> userIds = sellerService.getUsersBlacklisted(sellerId);
            userService.deleteBlacklistedSellers(userIds, sellerId);

            List<UUID> productIds = sellerService.getProductsBySeller(sellerId);
            for (UUID productId: productIds
                 ) { deleteProduct(String.valueOf(productId));

            }
            sellerService.deleteSeller(sellerId);

        }




}
