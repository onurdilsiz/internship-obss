package com.example.contactapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/search-contact")
public class SearchContactServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("search-contact.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String searchSQL = "SELECT * FROM Contacts WHERE name LIKE ? ";
        try  {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactDB", "root", "root");
            PreparedStatement preparedStatement = conn.prepareStatement(searchSQL);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();


            List<Map<String, String>> contacts = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, String> contact = new HashMap<>();
                contact.put("id", String.valueOf(resultSet.getInt("id")));
                contact.put("name", resultSet.getString("name"));
                contact.put("tel_number", resultSet.getString("tel_number"));
                contacts.add(contact);
            }

            if (contacts.isEmpty()) {
                resp.sendRedirect("no-contact.jsp");
            } else {
                req.setAttribute("contacts", contacts);
                RequestDispatcher dispatcher = req.getRequestDispatcher("search-contact.jsp");
                dispatcher.forward(req, resp);
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



}

