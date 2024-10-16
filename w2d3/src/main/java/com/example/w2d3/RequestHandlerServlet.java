package com.example.w2d3;


import java.io.IOException;
import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "req-handler", value = "/req-handler/*")
public class RequestHandlerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }



    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        if (requestURI.contains("secured")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied to secured content.");
        } else {
            request.getRequestDispatcher("/static.html").forward(request, response);
        }
    }
}
