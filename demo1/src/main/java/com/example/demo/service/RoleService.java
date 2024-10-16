package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.List;

public interface RoleService {
    public void checkAndCreateRoles(List<String> roles);
    Role findByName(String name);

    }
