package com.example.service;


import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface UserService {
        @WebMethod
        User getUser(String username);

        @WebMethod
        void addUser(User user);

        @WebMethod
        void updateUser(User user);

        @WebMethod
        void deleteUser(String username);

}
