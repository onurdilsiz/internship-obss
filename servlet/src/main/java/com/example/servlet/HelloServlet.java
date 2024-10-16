package com.example.servlet;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

public class HelloServlet extends HttpServlet {
    private String message;


        public void init() {
            message = "Hello World!";
        }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        System.out.println("Service");
//        PrintWriter out = resp.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + message +"Service"+ "</h1>");
//        out.println("</body></html>");
        super.service(req, resp);

    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

//        String destination = request.getParameter("destination");
//        if (destination == null || destination.isEmpty()) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Destination parameter is missing or empty");
//            return;
//        }
//        String jspPath = "/" + destination + ".jsp";
//        request.getRequestDispatcher(jspPath).forward(request, response);

        PrintWriter out = response.getWriter();

//        out.println("<html><body>");
//        out.println("<p style='font-weight: bold; color: blue;'>");
//        out.println("<a href='https://www.google.com' style='color: blue;'>Go to Google</a>");
//        out.println("</p>");
//        out.println("</body></html>");
//
//        if (name != null && !name.isEmpty()) {
//            out.println("<h2>Hello, " + name + "!</h2>");
//        } else {
//            out.println("<h2>Please enter your name and submit the form.</h2>");
//        }
//
//        // Hello
        out.println("<html><body>");
        out.println("<h1>" + message +"doGet"+ "</h1>");
        out.println("</body></html>");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String name = request.getParameter("name");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + name +" doPost"+ "</h1>");
        out.println("</body></html>");
    }


        public void destroy() {
    }
}