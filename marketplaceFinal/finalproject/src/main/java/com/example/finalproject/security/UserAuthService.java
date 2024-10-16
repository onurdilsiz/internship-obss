package com.example.finalproject.security;


import com.example.finalproject.model.User;
import com.example.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserAuthService implements UserDetailsService {

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final User user = userService.findUserbyUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new UserAuthDetails(user);
    }
}