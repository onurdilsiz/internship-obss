package com.example.w2d2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Servlet extends HttpServlet {
    private int balance = 100;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        int amount = Integer.parseInt(request.getParameter("amount"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
//
//        synchronized (this) {
//            try {
//                Thread.sleep(1500); // Simulate processing delay
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            if ("deposit".equals(action)) {
//                balance += amount;
//            } else if ("withdraw".equals(action)) {
//                balance -= amount;
//            }

            try {
            Thread.sleep(5000); // Simulate processing delay
        } catch (InterruptedException e) {
            e.printStackTrace();
            out.println("<h1>withdraw</h1>");

        }
        if ("deposit".equals(action)) {


            balance += amount;
        } else if ("withdraw".equals(action)) {

            if(balance>=amount) {
                balance -= amount;
            } else {
                out.println("<h1>not enough balance</h1>");


            }
        }


        out.println("<html><body>");
        out.println("<h1>Current balance: " + balance + "</h1>");
        out.println("</body></html>");    }

}

