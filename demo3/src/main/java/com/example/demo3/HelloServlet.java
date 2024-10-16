package com.example.demo3;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");


        Cookie cookie1 = new Cookie("cookie1", "value1");
        Cookie cookie2 = new Cookie("cookie2", "value2");
        Cookie cookie3 = new Cookie("cookie3", "value3");

        cookie1.setMaxAge(60 * 60);
        cookie2.setMaxAge(60 * 20);
        cookie3.setMaxAge(60 * 10);

        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.addCookie(cookie3);

        // Redirect to another servlet
//        RequestDispatcher dispatcher = request.getRequestDispatcher("target-servlet");
//        dispatcher.forward(request, response);
        response.sendRedirect("target-servlet");
    }

    public void destroy() {
    }
}