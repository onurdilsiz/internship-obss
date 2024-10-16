package day5.dbtest;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/contacts", "root", "root")){
            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT country, AVG(creditlimit) AS average_creditlimit FROM customers WHERE  creditlimit >5000  GROUP BY country HAVING average_creditlimit >100000");

//            String createTableSQL = "CREATE TABLE IF NOT EXISTS Contact (" +
//                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
//                    "firstName VARCHAR(50), " +
//                    "lastName VARCHAR(50), " +
//                    "phoneNumber VARCHAR(15))";
//            statement.executeUpdate(createTableSQL);
            System.out.println("Contact table created successfully.");

            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = null;

            String insertSQL = "INSERT INTO Contact (firstName, lastName, phoneNumber) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertSQL);
            PreparedStatement preparedStatement2 = connection.prepareStatement(insertSQL);

            preparedStatement.setString(1, "John");
            preparedStatement.setString(2, "Doe");
            preparedStatement.setString(3, "123456789");

            preparedStatement2.setString(1, "Alex");
            preparedStatement2.setString(2, "Soe");
            preparedStatement2.setString(3, "987654321");
            preparedStatement.executeUpdate();
            preparedStatement2.executeUpdate();
            connection.commit();
            System.out.println("Contact inserted successfully.");


            Statement statement2 = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT firstName FROM Contact  WHERE firstName LIKE '%le%'");

            statement = connection.createStatement();

            String updateSQL = "UPDATE Contact SET firstName = 'Ali' WHERE phoneNumber = 123456789";
            int rowsAffected = statement.executeUpdate(updateSQL);
            connection.commit();

            System.out.println("Number of rows updated: " + rowsAffected);
               while(resultSet.next()) {
                   String firstname = resultSet.getString("firstName");

                   System.out.println(firstname);
               }
////

//                System.out.println("First Name: " + firstName + ", Last Name: " + lastName+" "+ country);
////
////

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        }
}
