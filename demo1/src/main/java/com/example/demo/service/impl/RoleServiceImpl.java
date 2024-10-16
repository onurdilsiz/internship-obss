package com.example.demo.service.impl;

import com.example.demo.exception.RoleNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import com.example.demo.utils.CollectionUtils;
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
