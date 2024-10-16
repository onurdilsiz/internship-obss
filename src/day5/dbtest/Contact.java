package day5.dbtest;

import java.sql.*;
import java.util.Scanner;

public class Contact {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/contacts", "root", "root")){
            Statement statement = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS contacts (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "firstName VARCHAR(50), " +
                    "lastName VARCHAR(50), " +
                    "email VARCHAR(50))";
            statement.executeUpdate(createTableSQL);
            System.out.println("Contact table created.");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Enter the action: search, insert, update, delete or exit:");
                String action = scanner.nextLine();

                switch (action) {
                    case "insert":
                        System.out.println("Please enter contact name, surname and email separated by commas:");
                        String[] contactDetails = scanner.nextLine().split(",");
                        insertContact(connection, contactDetails[0], contactDetails[1], contactDetails[2]);
                        break;
                    case "search":
                        System.out.println("Please enter the contact name you want to search:");
                        String name = scanner.nextLine();
                        searchContacts(connection, name);
                        break;
                    case "update":
                        System.out.println("Please enter contact id you want to update:");
                        int updateId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Please enter new contact name, surname and email separated by commas:");
                        String[] newContactDetails = scanner.nextLine().split(",");
                        updateContact(connection, updateId, newContactDetails[0], newContactDetails[1], newContactDetails[2]);
                        break;
                    case "delete":
                        System.out.println("Please enter contact id you want to delete:");
                        int deleteId = Integer.parseInt(scanner.nextLine());
                        deleteContact(connection, deleteId);
                        break;
                    case "exit":
                        return;
                    default:
                        System.out.println("Invalid action. Please try again.");
                }
            }
////

//                System.out.println("First Name: " + firstName + ", Last Name: " + lastName+" "+ country);
////
////

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static void insertContact(Connection connection, String firstName, String lastName, String email) {
        String insertSQL = "INSERT INTO contacts (firstName, lastName, email) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println("Saved " + rowsInserted + " rows.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void searchContacts(Connection connection, String name) {
        String searchSQL = "SELECT * FROM contacts WHERE firstName LIKE ? OR lastName LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(searchSQL)) {
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                System.out.println("Contact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateContact(Connection connection, int id, String newFirstName, String newLastName, String newEmail) {
        String updateSQL = "UPDATE contacts SET firstName = ?, lastName = ?, email = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, newFirstName);
            preparedStatement.setString(2, newLastName);
            preparedStatement.setString(3, newEmail);
            preparedStatement.setInt(4, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println("Updated " + rowsUpdated + " rows.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteContact(Connection connection, int id) {
        String deleteSQL = "DELETE FROM contacts WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println("Deleted " + rowsDeleted + " rows.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
