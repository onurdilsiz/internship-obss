package com.example.service;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "UserServiceServlet", urlPatterns = {"/UserService"})
public class HelloServlet extends HttpServlet {
    private String message;
    @Override
    public void init() throws ServletException {
        super.init();

        System.out.println("Web Service is published at http://localhost:8080/YourWebAppName/UserService");
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        out.println("Web Service is running");
    }

    public void destroy() {
    }
}