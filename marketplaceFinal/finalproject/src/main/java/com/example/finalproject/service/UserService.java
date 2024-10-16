package com.example.finalproject.service;

import com.example.finalproject.dto.SellerDto;
import com.example.finalproject.dto.UserDto;
import com.example.finalproject.dto.ProductDto;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.Role;
import com.example.finalproject.model.Seller;
import com.example.finalproject.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserDto> searchUsers(String search, Integer page, Integer size, String sort);
    public UserDto createNewUser(UserDto userDto);

    Boolean isAdmin(String username);

    UserDto updateExistingUser(String userId,UserDto userDto);

    void deleteUser(String userId);

    UserDto findUserbyId(String userId);
//
    void generateSampleUsers(int targetUserSize);
//
    void checkandCreateAdminUser();

    Optional<User> findUserbyUsername(String username);
    UserDto findUserDtobyUsername(String username);

    void    updateExistingUserAsAdmin(String userId);

    List<ProductDto> addProductToFavorites(String username, String productId);

    List<ProductDto> getFavoriteProducts(String username);


    List<ProductDto> removeProductFromFavorites(String username, String productId);
    List<SellerDto> getBlacklistedSellers(String username);


    List<SellerDto> addSellerToBlacklist(String username, String sellerId);
    List<SellerDto> removeSellerFromBlacklist(String username, String sellerId);
     List<ProductDto> getProductsExcludingBlacklistedSellers(String userId) ;

     void deleteFavoriteProducts( List<UUID> userIds,String productId);
     void deleteBlacklistedSellers( List<UUID> userIds,String sellerId);



    }
