package com.example.contactapp;



import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.*;
import jakarta.servlet.http.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

@WebServlet("/edit-contact")

public class EditContactServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-contact.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = (String) req.getParameter("id");
        String name = (String ) req.getParameter("name");
        String number = (String ) req.getParameter("tel_number");
        String updateSQL = "UPDATE Contacts SET name = ? ,tel_number = ? WHERE id = ?";

        try  {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactDB", "root", "root");
            PreparedStatement preparedStatement = conn.prepareStatement(updateSQL);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, Integer.parseInt( number));
            preparedStatement.setInt(3, Integer.parseInt( id));
            int rowsUpdated =  preparedStatement.executeUpdate();
            System.out.println("Updated " + rowsUpdated + " rows.");

            conn.close();
            resp.sendRedirect("success.jsp");

        } catch (SQLException ex) {
            ex.printStackTrace();

            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }








    public static void updateContact(Connection connection, int id, String newName, int newNumber) {
        String updateSQL = "UPDATE Contacts SET name = ? ,tel_number = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, newNumber);
            preparedStatement.setInt(3, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println("Updated " + rowsUpdated + " rows.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
