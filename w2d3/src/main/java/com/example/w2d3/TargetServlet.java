package com.example.w2d3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;

@WebServlet(name = "target", value = "/target")

public class TargetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestAttribute = (String) request.getAttribute("requestAttribute");
        response.getWriter().println("Request Attribute: " + requestAttribute);

        // Check ServletContext attribute
        String contextAttribute = (String) getServletContext().getAttribute("contextAttribute");
        response.getWriter().println("Context Attribute: " + contextAttribute);




    }


}
