package ke.co.safaricom.Models;
import java.sql.*;
import ke.co.safaricom.DB.DB;
import spark.Spark;

import org.sql2o.Sql2o;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogin {
    private String email;
    private String password;

    public UserLogin(){
        this.email =email;
        this.password = password;
    }

    public String getEmail(){return email;}
    public void setEmail(String email){this.email =email;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}

  /*  public boolean isValidUser(String email, String password){
        String query = "SELECT * FROM  loginCredentials WHERE email=? AND password=?";

        try (Connection connection = DriverManager.getConnection(query);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check if the result set has any rows
                if (resultSet.next()) {
                    // User exists, login is valid
                    return true;
                } else {
                    // User does not exist, login failed
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection or query execution error
            return false;
        }*/
        public boolean isValidUser(String email, String password) {
            String query = "SELECT * FROM loginCredentials WHERE email=? AND password=?";


            try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Moringa@122023");
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Check if the result set has any rows
                    return resultSet.next(); // Returns true if a row is found, false otherwise
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle database connection or query execution error
                return false;
            }
        }

    }

