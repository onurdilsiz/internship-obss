package com.example.demo.service.impl;

import com.example.demo.common.Constants;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

import static com.example.demo.common.Constants.Roles.ROLE_USER;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

//    private Map<String, UserDto> users = new HashMap<>();
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

//    @PostConstruct
//    void init(){
//
//        for (int i = 0; i< 100; i++) {
//            final UserDto userDto = UserDto .builder()
//                    .name(StringUtils.generateRandomString( 20))
//            .surname(StringUtils.generateRandomString(  15))
//            .email(StringUtils.generateRandomString( 10 )+ "@gmail.com")
//            .build();
//        userDto.setUsername(toUsername(userDto.getName(),userDto.getSurname()));
//        userRepository.save(userMapper.toModel(userDto));
//        }
////        log.info("ben calistim");
////        for(int i = 0 ; i<10; i++){
////            final String userId = UUID.randomUUID().toString();
//////            users.put(userId, UserDto.builder().id(userId)
//////                    .surname("guney"+i)
//////                    .name("guney")
//////                    .email("guney@obss.comm")
//////                    .username("guney")
//////                    .build());
////            userRepository.save(UserDto.builder().id(userId)
////                    .surname("guney"+i)
////                    .name("guney")
////                    .email("guney@obss.comm")
////                    .username("guneykhkhkhg")
////                    .build());
//
//
//    }

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



//        }final List<UserDto> list = users
//                .values().stream().filter(userDto -> StringUtils.isBlank(search) || userDto.getName().contains(search) ||
//                        userDto.getSurname().contains(search)) .toList();
//        if (list.isEmpty()) {
//            return list;
//        }
//        int pageVal = 0;
//        int sizeVal = 10;
//
//        if(Objects.nonNull(page)){
//            pageVal = page;
//        }
//
//        if(Objects.nonNull(size)){
//            sizeVal = page;
//        }
//
//
//
//        return list.subList(page, Math.min((page * size) + size, list.size()));


    public  UserDto findUserbyId(@PathVariable("userId") String userId){
        Objects.requireNonNull(userId,"userOd cannot be null");
        return userMapper.toDto(userRepository.findUserById(UUID.fromString(userId)).orElseThrow(() -> new UserNotFoundException(userId)));
//        Optional.ofNullable(users.get(userId)).orElseThrow(() -> new UserNotFoundException(userId));
    }

    private String toUsername(String name, String surname){
        return String.format("%s.%s", name. replaceAll(  "\\s+",  "").toLowerCase(), surname.replaceAll( "\\s+",  "").toLowerCase());
    }
    @Transactional
    @Override
    public UserDto createNewUser(UserDto userDto) {
        Objects.requireNonNull(userDto,"userDto must not be null");
        Objects.requireNonNull(userDto.getName(),"userDto name must not be null");
        Objects.requireNonNull(userDto.getSurname(),"userDto surname must not be null");

        userDto.setId(UUID.randomUUID().toString());

        if (StringUtils.isBlank(userDto.getUsername())) {
            userDto.setUsername(toUsername(userDto.getName(), userDto.getSurname()));
        }
        User user = userMapper.toModel(userDto);
        user.setPassword(passwordEncoder.encode("123456"));

        final Role userRole = roleService.findByName(ROLE_USER);
        user.addRole(userRole);
        return userMapper.toDto(userRepository.save(user));

    }

    @Override
    public UserDto updateExistingUser(String userId,UserDto userDto) {

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

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(String userId) {
        Objects.requireNonNull(userId, "user can not be null");
        final User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(()-> new UserNotFoundException(userId));
        userRepository.delete(user);
    }


    public void generateSampleUsers(int targetUserSize){
        List<User> users = new ArrayList<>();


        for (int i = 0; i< targetUserSize; i++) {
            final UserDto userDto = UserDto .builder()
                    .name(StringUtils.generateRandomString( 20))
                    .surname(StringUtils.generateRandomString(  15))
                    .email(StringUtils.generateRandomString( 10 )+ "@gmail.com")
                    .build();
            userDto.setUsername(toUsername(userDto.getName(),userDto.getSurname()));
            final User user = userMapper.toModel(userDto);
            user.setPassword(passwordEncoder.encode("pass"));
            final Role userRole = roleService.findByName(ROLE_USER);
            user.addRole(userRole);

            users.add(user);

        }
        userRepository.saveAll(users);


    }

    public void checkandCreateAdminUser() {
        userRepository.findUserByUsername(Constants.ADMIN_USER).orElseGet(() -> {

                    final Role adminRole = roleService.findByName(Constants.Roles.ROLE_ADMIN);
                    User user = new User();
                    user.setUsername(Constants.ADMIN_USER);
                    user.setEnabled(Boolean.TRUE);
                    user.setName("admin");
                    user.setSurname("admin");
                    user.setEmail("admin@asdf.com");
                    user.setPassword(passwordEncoder.encode("pass"));
                    user.addRole(adminRole);
                    userRepository.save(user);
                    return user;
                }


        );
    }

    @Override
    public Optional<User> findUserbyUsername(String username) {
        Objects.requireNonNull(username,"userOd cannot be null");
        return userRepository.findUserByUsername(username);

    }

    @Override
    @Transactional
    public void updateExistingUserAsAdmin(String userId) {
        final User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(()-> new UserNotFoundException(userId));
        final Role adminRole = roleService.findByName(Constants.Roles.ROLE_ADMIN);
        user.addRole(adminRole);
        userRepository.save(user);
    }


}
