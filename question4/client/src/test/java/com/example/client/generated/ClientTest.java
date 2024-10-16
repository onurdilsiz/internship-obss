package com.example.client.generated;

import org.junit.Test;
import static org.junit.Assert.*;
public class ClientTest {

    public static void main(String[] args) {

        // Create the service and port objects



        UserServiceImplService service = new UserServiceImplService();
        UserServiceImpl userService = service.getUserServiceImplPort();


        // Test addUser method
        User newUser = new User();
        newUser.setUsername("NewUser");
        newUser.setEmail("newuser@example.com");
        userService.addUser(newUser);
        System.out.println("User added successfully");

        // Test getUser method
        User user = userService.getUser("NewUser");
        System.out.println("User Retrieved: " + user.getUsername());


        // Test updateUser method
        newUser.setUsername("UpdatedUser");
        userService.updateUser(newUser);
        System.out.println("User updated successfully");

        // Test deleteUser method
        userService.deleteUser("NewUser");
        System.out.println("User deleted successfully");
    }
}
