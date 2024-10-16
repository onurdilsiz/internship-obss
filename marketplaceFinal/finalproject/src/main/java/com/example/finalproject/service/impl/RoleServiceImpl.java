package com.example.finalproject.service.impl;

import com.example.finalproject.exception.RoleNotFoundException;
import com.example.finalproject.model.Role;
import com.example.finalproject.repository.RoleRepository;
import com.example.finalproject.service.RoleService;
import com.example.finalproject.utils.CollectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;


    public void checkAndCreateRoles(List<String> roles){
        if(CollectionUtils.isEmpty(roles)){
            return;
        }
        roles.forEach(role -> {
            roleRepository.findByName(role).orElseGet(()->roleRepository.save(new Role(role)));
        } );

    }

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName).orElseThrow(()->new RoleNotFoundException(roleName));
    }
}
