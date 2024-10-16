package com.example.w2d3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;

@WebServlet(name = "origin", value = "/origin")

public class OriginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HttpServletRequest Attributes Exercise
        request.setAttribute("requestAttribute", "requestValue");
        getServletContext().setAttribute("contextAttribute", "contextValue");

//         response.sendRedirect("target");


        // Forward request to TargetServlet
        RequestDispatcher dispatcher = request.getRequestDispatcher("/target");
        dispatcher.forward(request, response);
    }


}
