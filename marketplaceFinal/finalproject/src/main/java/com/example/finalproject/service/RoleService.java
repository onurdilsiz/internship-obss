package com.example.finalproject.service;

import com.example.finalproject.model.Role;

import java.util.List;

public interface RoleService {
    public void checkAndCreateRoles(List<String> roles);
    Role findByName(String name);
}
