package com.example.w2d3;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        StringBuffer requestURL = request.getRequestURL();
        String reqURI = request.getRequestURI();
        String servletPath = request.getServletPath();
        String contextPath = request.getContextPath();
        String pathInfo = request.getPathInfo();
        String queryString = request.getQueryString();
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");

        out.println("<p>"+requestURL+"</p>");
        out.println("<p>"+reqURI+"</p>");

        out.println("<p>"+servletPath+"</p>");

        out.println("<p>"+"context"+contextPath+"</p>");

        out.println("<p>"+pathInfo+"</p>");

        out.println("<p>"+queryString+"</p>");
        String requestAttribute = (String) request.getAttribute("requestAttribute");
        response.getWriter().println("Request Attribute: " + requestAttribute);


        out.println("</body></html>");
    }

    public void destroy() {
    }
}