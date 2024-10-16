package com.example.w2d3;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "include", value = "/include")
public class IncludeServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/hello-servlet");
        req.setAttribute("requestAttribute", "requestValue");

        requestDispatcher.include(req, res);

        // You can continue writing additional content to the response if needed

        res.getWriter().println("Content from IncludeServlet.");



    }

    public void destroy() {
    }
}