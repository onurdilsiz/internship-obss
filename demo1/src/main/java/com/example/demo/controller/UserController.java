package com.example.demo.controller;

import com.example.demo.dto.BaseResponse;
import com.example.demo.dto.CreateNewUserRequest;
import com.example.demo.dto.UpdateExistingUserRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.*;
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
   private  UserService userService;

    @Qualifier("UserServiceImpl")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    @Operation(summary = "Get users by parameters")
    @GetMapping()
    public BaseResponse<List<UserDto>> searchUsers(@RequestParam(name = "search", required = false) String search,
                                                  @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
                                                  @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(value = "sort", required = false) String sort)
    {
        return new BaseResponse<>(userService.searchUsers(search,page,size,sort));

    }
//
//        @GetMapping()
//    public  List<UserDto> searchUsers(@RequestParam(value = "search",required = false) String search){
//        final list users.values().stream().filter(userDto -> userDto.getName().contains(search)|| userDto.getSurname().contains(search)).toList();
//
//        return users.values().stream().toList();
//    }
    @GetMapping("/{userId}")
    public  BaseResponse<UserDto> getUserDetail(@PathVariable("userId") String userId){
        return new BaseResponse<>(userService.findUserbyId(userId));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public BaseResponse<UserDto> createNewUser(@RequestBody @Valid CreateNewUserRequest createNewUserRequest){
        final UserDto userDto = UserDto.builder()
                .name(createNewUserRequest.getName())
                .surname(createNewUserRequest.getSurname())
                .email(createNewUserRequest.getEmail())
                .username(createNewUserRequest.getUsername()).build();


        return new BaseResponse<>( userService.createNewUser(userDto));

//            Objects.requireNonNull(createNewUserRequest, "user can not be null");
//        Objects.requireNonNull(createNewUserRequest.getEmail(), "email cannot be null");
//        createNewUserRequest.setId(UUID.randomUUID().toString());
//        users.put(createNewUserRequest.getId(),createNewUserRequest);
//        return user;

    }




    @PutMapping("/{userId}")
    public BaseResponse< UserDto > updateUser(@PathVariable("userId") String userId , @RequestBody @Valid UpdateExistingUserRequest user){
//        Objects.requireNonNull(user, "user can not be null");
//        Objects.requireNonNull(user.getEmail(), "email cannot be null");
        UserDto userDto = UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail()).build();
        UserDto userUpdate = userService.updateExistingUser(userId,userDto);

        return new BaseResponse<>(userUpdate);
    }

    @PutMapping("/{userId}/assign-as-admin")
    public BaseResponse< Boolean > updateUserAsAdmin(@PathVariable("userId") String userId , @RequestBody @Valid UpdateExistingUserRequest user){
        userService.updateExistingUserAsAdmin(userId);
//        Objects.requireNonNull(user, "user can not be null");
//        Objects.requireNonNull(user.getEmail(), "email cannot be null");
//        UserDto userDto = UserDto.builder()
//                .name(user.getName())
//                .surname(user.getSurname())
//                .email(user.getEmail()).build();
//        UserDto userUpdate = userService.updateExistingUser(userId,userDto);

        return new BaseResponse<>(Boolean.TRUE);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId){
        Objects.requireNonNull(userId, "user can not be null");
        userService.deleteUser(userId);
    }


}
