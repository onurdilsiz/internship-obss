package com.example.demo.common;

import com.example.demo.model.Post;
import com.example.demo.model.PostComment;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.example.demo.common.Constants.Roles.ROLE_ADMIN;


@RequiredArgsConstructor
@Configuration
public class DataLoader implements ApplicationRunner {
    private final UserService userService;
    private final RoleService roleService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        int targetUserSize= 2;
        roleService.checkAndCreateRoles(List.of(ROLE_ADMIN, Constants.Roles.ROLE_USER));

        userService.generateSampleUsers(targetUserSize);
        userService.checkandCreateAdminUser();




    }
}
