package com.example.w2d2;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

public class HelloServlet extends HttpServlet {
    private String message;
    private String method;


    public void init() {
        message = "Hello World!";
        method = getServletConfig().getInitParameter("method");

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if ("forward".equalsIgnoreCase(method)) {
            getServletContext().getRequestDispatcher("/forward.jsp").forward(request, response);
        } else if ("redirect".equalsIgnoreCase(method)) {
            response.sendRedirect(request.getContextPath() + "/redirected.jsp");
        } else {
            // Geçersiz method parametresi
            response.getWriter().println("Geçersiz yönlendirme methodu: " + method);
        }

    }

    public void destroy() {
    }
}