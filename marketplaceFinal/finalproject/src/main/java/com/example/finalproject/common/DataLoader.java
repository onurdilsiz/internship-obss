package com.example.finalproject.common;

import com.example.finalproject.dto.SellerDto;
import com.example.finalproject.service.ProductService;
import com.example.finalproject.service.RoleService;
import com.example.finalproject.service.SellerService;
import com.example.finalproject.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.example.finalproject.common.Constants.Roles.ROLE_ADMIN;
import static com.example.finalproject.common.Constants.Roles.ROLE_USER;

@Configuration
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final UserService userService;
    private final RoleService roleService;
    private final ProductService productService;
    private final SellerService sellerService;


    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        int targetUserSize= 10;
        roleService.checkAndCreateRoles(List.of(ROLE_ADMIN, ROLE_USER));

        userService.generateSampleUsers(targetUserSize);
        int targetProductSize = 10;

        List<SellerDto> sellerIds = sellerService.generateSampleSellers(10);

        productService.generateSampleProducts(targetProductSize, sellerIds);

        userService.checkandCreateAdminUser();





    }
}
