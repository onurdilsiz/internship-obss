package com.example.demo.service;


import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> searchUsers(String search, Integer page, Integer size,String sort);
    public UserDto createNewUser(UserDto userDto);

    UserDto updateExistingUser(String userId,UserDto userDto);

    void deleteUser(String userId);

    UserDto findUserbyId(String userId);

    void generateSampleUsers(int targetUserSize);

    void checkandCreateAdminUser();



    Optional<User> findUserbyUsername(String username);

    void updateExistingUserAsAdmin(String userId);
}
