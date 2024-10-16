package com.example.demo3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "targetServlet", value = "/target-servlet")

public class TargetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get cookies from the request
        Cookie[] cookies = request.getCookies();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                out.println("<p>" + cookie.getName() + ": " + cookie.getValue() + "</p>");
            }
        } else {
            out.println("<p>No cookies found.</p>");
        }
    }
}
