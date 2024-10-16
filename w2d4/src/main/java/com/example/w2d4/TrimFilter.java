package com.example.w2d4;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/login")
public class TrimFilter implements Filter {

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//
//        if (req.getMethod().equalsIgnoreCase("POST")) {
//
//            String username = req.getParameter("username");
//            String password = req.getParameter("password");
//
//            // Check if username and password contain whitespace
//            if (containsWhitespace(username) || containsWhitespace(password)) {
//                // If any parameter contains whitespace, send an error response
//                resp.sendError(400);
//
//
//                return;
//            }
//        }
//
//        // Continue with the request
//        chain.doFilter(request, response);
//
//
//    }


    @Override
    public void destroy() {
    }
    private boolean containsWhitespace(String str) {
        return str != null && str.matches(".*\\s.*");
    }

}
