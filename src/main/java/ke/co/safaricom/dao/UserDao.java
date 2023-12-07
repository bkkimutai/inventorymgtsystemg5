package ke.co.safaricom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLInput;
import ke.co.safaricom.Models.CreateUser;
public class UserDao {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/root";

    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "Moraa@2019";

    public boolean addUser(CreateUser newUser) {
        String sql = "INSERT INTO users (firstname, lastname, email, company, roles, phonenumber) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, newUser.getFirstName());
            preparedStatement.setString(2, newUser.getLastName());
            preparedStatement.setString(3, newUser.getEmail());
            preparedStatement.setString(4, newUser.getCompany());
            preparedStatement.setString(5, newUser.getRoles());
            preparedStatement.setString(6, newUser.getPhoneNumber());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            // Handle exceptions, log, or throw as appropriate
            e.printStackTrace();
        }
        return false;
    }
}

