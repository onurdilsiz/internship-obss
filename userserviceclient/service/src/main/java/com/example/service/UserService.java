package com.example.service;

import com.example.service.model.User;

import javax.jws.WebService;
import javax.jws.WebMethod;
@WebService
public interface UserService {

    @WebMethod
    void insertUser(User user);

    @WebMethod
    User selectUser(String username);

    @WebMethod
    void updateUser(User user);

    @WebMethod
    void deleteUser(String username);
}
