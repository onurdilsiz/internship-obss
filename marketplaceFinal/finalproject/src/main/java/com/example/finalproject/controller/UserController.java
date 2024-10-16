package com.example.finalproject.controller;
import com.example.finalproject.dto.*;

import com.example.finalproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

        @Autowired
        private UserService userService;


        @Operation(summary = "Get users by parameters")
        @PreAuthorize("hasRole('ROLE_ADMIN')")
        @GetMapping()
        public BaseResponse<List<UserDto>> searchUsers(@RequestParam(name = "search", required = false) String search,
                                                       @RequestParam(value = "size", required = false, defaultValue = "100") Integer size,
                                                       @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                       @RequestParam(value = "sort", required = false) String sort)
        {
                return new BaseResponse<>(userService.searchUsers(search,page,size,sort));

        }

        @PreAuthorize("hasRole('ROLE_ADMIN')")
        @PostMapping
        public BaseResponse<UserDto> createNewUser(@RequestBody @Valid CreateNewUserRequest createNewUserRequest){
                final UserDto userDto = UserDto.builder()
                        .name(createNewUserRequest.getName())
                        .surname(createNewUserRequest.getSurname())
                        .email(createNewUserRequest.getEmail())
                        .username(createNewUserRequest.getUsername())
                        .password(createNewUserRequest.getPassword())
                        .build();



                return new BaseResponse<>( userService.createNewUser(userDto));



        }

        @GetMapping("/{userId}")
        public  BaseResponse<UserDto> getUserDetail(@PathVariable("userId") String userId){
                return new BaseResponse<>(userService.findUserbyId(userId));
        }

        @GetMapping("/username/{username}")
        public  BaseResponse<UserDto> getUserDetailByUsername(@PathVariable("username") String username) {
                return new BaseResponse<>(userService.findUserDtobyUsername(username));
        }
        @GetMapping("/userrole/{username}")
        public  BaseResponse<Boolean> isAdmin(@PathVariable("username") String username){
                return new BaseResponse<>(userService.isAdmin(username));
        }


        @PreAuthorize("hasRole('ROLE_ADMIN')")
        @PutMapping("/{userId}")
        public BaseResponse< UserDto > updateUser(@PathVariable("userId") String userId , @RequestBody @Valid UpdateExistingUserRequest user){

                UserDto userDto = UserDto.builder()
                        .name(user.getName())
                        .surname(user.getSurname())
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .build();

                UserDto userUpdate = userService.updateExistingUser(userId,userDto);

                return new BaseResponse<>(userUpdate);
        }

        @PreAuthorize("hasRole('ROLE_ADMIN')")
        @DeleteMapping("/{userId}")
        public void deleteUser(@PathVariable String userId){
                Objects.requireNonNull(userId, "user can not be null");
                userService.deleteUser(userId);
        }

        @PreAuthorize("hasRole('ROLE_ADMIN')")
        @PutMapping("/{userId}/assign-as-admin")
        public BaseResponse< Boolean > updateUserAsAdmin(@PathVariable("userId") String userId ){
                userService.updateExistingUserAsAdmin(userId);

                return new BaseResponse<>(Boolean.TRUE);
        }

        @GetMapping("/{username}/favorite-products")
        public BaseResponse< List<String>  > getFavoriteProducts(@PathVariable("username") String username){

                List<String> productIds = userService.getFavoriteProducts(username).stream()
                        .map(ProductDto::getId)
                        .collect(Collectors.toList());
                return new BaseResponse<>(productIds);
        }

        @GetMapping("/{username}/favorite-products-details")
        public BaseResponse< List<ProductDto>  > getFavoriteProductDetails(@PathVariable("username") String username){

                List<ProductDto> products = userService.getFavoriteProducts(username);
                return new BaseResponse<>(products);
        }

        @PostMapping("/{username}/favorite-products/{productId}")
        public BaseResponse< List<ProductDto> > addProductToFavorites(@PathVariable("username") String username , @PathVariable("productId") String productId){

                List<ProductDto> productDto = userService.addProductToFavorites(username,productId);
                return new BaseResponse<>(productDto);
        }

        @DeleteMapping("/{username}/favorite-products/{productId}")
        public BaseResponse< List<ProductDto> > removeProductFromFavorites(@PathVariable("username") String username , @PathVariable("productId") String productId){

                List<ProductDto> productDto = userService.removeProductFromFavorites(username,productId);
                return new BaseResponse<>(productDto);
        }



        @GetMapping("/{username}/blacklist-seller")
        public BaseResponse<List<String>  > getBlacklistedSellers(@PathVariable("username") String username ) {

                List<String> sellerIds = userService.getBlacklistedSellers(username).stream()
                        .map(SellerDto::getId)
                        .collect(Collectors.toList());

                return new BaseResponse<>(sellerIds);
        }

        @GetMapping("/{username}/blacklist-seller-details")
        public BaseResponse<List<SellerDto>  > getBlacklistedSellerDetails(@PathVariable("username") String username ) {

                List<SellerDto> sellers = userService.getBlacklistedSellers(username);

                return new BaseResponse<>(sellers);
        }

        @PutMapping("/{username}/blacklist-seller/{sellerId}")
        public BaseResponse< List<SellerDto> > addSellerToBlacklist(@PathVariable("username") String username , @PathVariable("sellerId") String sellerId){

                List<SellerDto> seller = userService.addSellerToBlacklist(username,sellerId);
                return new BaseResponse<>(seller);
        }

        @DeleteMapping("/{username}/blacklist-seller/{sellerId}")
        public BaseResponse< List<SellerDto> > removeSellerFromBlacklist(@PathVariable("username") String username , @PathVariable("sellerId") String sellerId){

                List<SellerDto> seller = userService.removeSellerFromBlacklist(username,sellerId);
                return new BaseResponse<>(seller);
        }


        @GetMapping("/{username}/products")
        public BaseResponse<List<ProductDto>> getProductsExcludingBlacklistedSellers(@PathVariable("username") String username) {
                return new BaseResponse<>(userService.getProductsExcludingBlacklistedSellers(username));
        }



}
