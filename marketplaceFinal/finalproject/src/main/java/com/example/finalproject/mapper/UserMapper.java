package com.example.finalproject.mapper;

import com.example.finalproject.dto.UserDto;
import com.example.finalproject.model.User;
import com.example.finalproject.utils.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserMapper {

    public User toModel(UserDto dto){
        User user = new User();
        if(StringUtils.isNotBlank(dto.getId())){
            user.setId(UUID.fromString(dto.getId()));
        }
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setSurname(dto.getSurname());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;

    }
    public UserDto toDto(User model) {
        UserDto dto = new UserDto();
        dto.setName(model.getName());
        dto.setEmail(model.getEmail());
        dto.setSurname(model.getSurname());
        dto.setUsername(model.getUsername());
        dto.setPassword(model.getPassword());
        dto.setId(model.getId().toString());
        dto.setCreatedAt(model.getCreatedAt());
        dto.setUpdatedAt(model.getUpdatedAt());


        return dto;
    }
}


