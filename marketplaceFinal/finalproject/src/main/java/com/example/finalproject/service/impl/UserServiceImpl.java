package com.example.finalproject.service.impl;

import com.example.finalproject.common.Constants;
import com.example.finalproject.dto.ProductDto;
import com.example.finalproject.dto.SellerDto;
import com.example.finalproject.dto.UserDto;
import com.example.finalproject.exception.ProductNotFoundException;
import com.example.finalproject.exception.SellerNotFoundException;
import com.example.finalproject.exception.UserNotFoundException;
import com.example.finalproject.mapper.ProductMapper;
import com.example.finalproject.mapper.SellerMapper;
import com.example.finalproject.mapper.UserMapper;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.Role;
import com.example.finalproject.model.Seller;
import com.example.finalproject.model.User;
import com.example.finalproject.repository.ProductRepository;
import com.example.finalproject.repository.SellerRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.ProductService;
import com.example.finalproject.service.RoleService;
import com.example.finalproject.service.UserService;
import com.example.finalproject.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.finalproject.common.Constants.Roles.ROLE_ADMIN;
import static com.example.finalproject.common.Constants.Roles.ROLE_USER;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductMapper productMapper;
    private final SellerMapper sellerMapper;




    @Override
    public List<UserDto> searchUsers(String search, Integer page, Integer size, String sort) {
        String sortField = sort;
        if (StringUtils.isBlank(sort)) {
            sortField = "createdAt";
        }

        final PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sortField)));
        List<User> users = null;
        if (StringUtils.isBlank(search)) {
            users = userRepository.findAllUsers(pageRequest);
        } else {
            users = userRepository.findUsersByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(search, search, pageRequest);
        }
        return users.stream().map(userMapper::toDto).toList();
    }

    public  UserDto findUserbyId(String userId){
        Objects.requireNonNull(userId,"userOd cannot be null");
        return userMapper.toDto(userRepository.findUserById(UUID.fromString(userId)).orElseThrow(() -> new UserNotFoundException(userId)));
//        Optional.ofNullable(users.get(userId)).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public Optional<User> findUserbyUsername(String username) {
        Objects.requireNonNull(username,"username cannot be null");
        return Optional.ofNullable(userRepository.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException(username)));
    }

    @Override
    public UserDto findUserDtobyUsername(String username) {
        Objects.requireNonNull(username,"username cannot be null");
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return userMapper.toDto(user);
    }
    @Override
    public void updateExistingUserAsAdmin(String userId) {
        final User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(()-> new UserNotFoundException(userId));

        final Role adminRole = roleService.findByName(ROLE_ADMIN);
        user.addRole(adminRole);
        userRepository.save(user);

    }

    @Override
    public List<ProductDto> getFavoriteProducts(String username) {
        final User user = userRepository.findUserByUsername(username).orElseThrow(()-> new UserNotFoundException(username));

        return user.getFavoriteProducts().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<ProductDto> addProductToFavorites(String username, String productId) {
        final User user = userRepository.findUserByUsername(username).orElseThrow(()-> new UserNotFoundException(username));

//        final ProductDto product = productService.findProductById(productId);
        final Product product = productRepository.findProductById(UUID.fromString(productId) ).orElseThrow(()-> new ProductNotFoundException(productId));
        user.addFavoriteProduct(product);
        userRepository.save(user);


        return user.getFavoriteProducts().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<ProductDto> removeProductFromFavorites(String username, String productId) {
        final User user = userRepository.findUserByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
//        final ProductDto product = productService.findProductById(productId);
        final Product product = productRepository.findProductById(UUID.fromString(productId) ).orElseThrow(()-> new ProductNotFoundException(productId));

        user.removeFavoriteProduct(product);
        userRepository.save(user);


        return user.getFavoriteProducts().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteFavoriteProducts( List<UUID> userIds,String productId){
        for (UUID userId : userIds) {
            final User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId.toString()));
            final Product product = productRepository.findById(UUID.fromString(productId)).orElseThrow(() -> new ProductNotFoundException(productId.toString()));
            user.removeFavoriteProduct(product);
            userRepository.save(user);
        }
    }
    public void deleteBlacklistedSellers( List<UUID> userIds,String sellerId){
        for (UUID userId : userIds) {
            final User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId.toString()));
            final Seller seller = sellerRepository.findById(UUID.fromString(sellerId)).orElseThrow(() -> new SellerNotFoundException(sellerId.toString()));
            user.removeSellerFromBlacklist(seller);
            userRepository.save(user);
        }

    }


    @Override
    public List<SellerDto> getBlacklistedSellers(String username) {
        final User user = userRepository.findUserByUsername(username).orElseThrow(()-> new UserNotFoundException(username));

        return user.getBlacklist().stream()
                .map(sellerMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<SellerDto> addSellerToBlacklist(String username, String sellerId) {
        final User user = userRepository.findUserByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
        final Seller seller = sellerRepository.findSellerById(UUID.fromString(sellerId)).orElseThrow(()-> new SellerNotFoundException(sellerId));
        user.addSellerToBlacklist(seller);
        userRepository.save(user);

        return user.getBlacklist().stream()
                .map(sellerMapper::toDto)
                .collect(Collectors.toList());    }
    @Override
    public List<SellerDto> removeSellerFromBlacklist(String username, String sellerId) {
        final User user = userRepository.findUserByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
        final Seller seller = sellerRepository.findSellerById(UUID.fromString(sellerId)).orElseThrow(()-> new SellerNotFoundException(sellerId));
        user.removeSellerFromBlacklist(seller);
        userRepository.save(user);

        return user.getBlacklist().stream()
                .map(sellerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createNewUser(UserDto userDto){
        Objects.requireNonNull(userDto,"userDto must not be null");
        Objects.requireNonNull(userDto.getName(),"userDto name must not be null");
        Objects.requireNonNull(userDto.getSurname(),"userDto surname must not be null");
        Objects.requireNonNull(userDto.getPassword(),"userDto password must not be null");
        userDto.setId(UUID.randomUUID().toString());

        if (StringUtils.isBlank(userDto.getUsername())) {
            userDto.setUsername(toUsername(userDto.getName(), userDto.getSurname()));
        }
        User user = userMapper.toModel(userDto);
        user.setEnabled(Boolean.TRUE);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        final Role userRole = roleService.findByName(ROLE_USER);
        user.addRole(userRole);

        return userMapper.toDto(userRepository.save(user));

    }

    @Override
    public Boolean isAdmin(String username) {
        Objects.requireNonNull(username, "username cannot be null");
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

        return user.getRoles().stream().anyMatch(role -> role.getName().equals(ROLE_ADMIN));
    }

    @Override
    public UserDto updateExistingUser(String userId, UserDto userDto) {

        final User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(()-> new UserNotFoundException(userId));

        if(StringUtils.isNotBlank(userDto.getName())){
            user.setName(userDto.getName());
        }
        if(StringUtils.isNotBlank(userDto.getEmail())){
            user.setEmail(userDto.getEmail());
        }
        if(StringUtils.isNotBlank(userDto.getSurname())){
            user.setSurname(userDto.getSurname());
        }
        if(StringUtils.isNotBlank(userDto.getUsername())){
            user.setUsername(userDto.getUsername());
        }

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(String userId) {
        Objects.requireNonNull(userId, "user can not be null");
        final User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(()-> new UserNotFoundException(userId));
        userRepository.delete(user);

    }



    private String toUsername(String name, String surname){
        return String.format("%s.%s", name. replaceAll(  "\\s+",  "").toLowerCase(), surname.replaceAll( "\\s+",  "").toLowerCase());
    }

    public void generateSampleUsers(int targetUserSize) {
        List<User> users = new ArrayList<>();

        List<String> names = List.of("John", "Jane", "Michael", "Sarah", "David", "Emily", "Robert", "Jessica", "James", "Laura");
        List<String> surnames = List.of("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Martinez", "Lopez");


        for (int i = 0; i < targetUserSize; i++) {
            String name = names.get(i);
            String surname = surnames.get(i );
            String email = name.toLowerCase() + "." + surname.toLowerCase() + "@example.com";
            String password = passwordEncoder.encode("1234");

            final UserDto userDto = UserDto.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .build();
            userDto.setUsername(toUsername(userDto.getName(), userDto.getSurname()));
            final User user = userMapper.toModel(userDto);
            user.setEnabled(Boolean.TRUE);
            final Role userRole = roleService.findByName(ROLE_USER);
            user.addRole(userRole);

            users.add(user);
        }
        userRepository.saveAll(users);
    }

    @Override
    public void checkandCreateAdminUser() {
        final UserDto userDto = UserDto.builder()
                .name("admin")
                .surname("admin")
                .email("admin@admin.c")
                .password(passwordEncoder.encode("admin"))
                .build();
        userDto.setUsername(toUsername(userDto.getName(), userDto.getSurname()));
        final User user = userMapper.toModel(userDto);
        user.setEnabled(Boolean.TRUE);
        final Role userRole = roleService.findByName(ROLE_ADMIN);
        user.addRole(userRole);
        userRepository.save(user);

    }



    public List<ProductDto> getProductsExcludingBlacklistedSellers(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
        List<UUID> blacklistedSellerIds = user.getBlacklist().stream()
                .map(seller -> seller.getId())
                .collect(Collectors.toList());

        return productRepository.findAll().stream()
                .filter(product -> !blacklistedSellerIds.contains(product.getSeller().getId()))
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

}
