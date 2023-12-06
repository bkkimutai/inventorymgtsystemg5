package ke.co.safaricom.DB;
import org.sql2o.Sql2o;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/root", "postgres", "Moraa@2019");

    public static void main(String[] args) {

        String jdbcURL = "jdbc:postgresql://localhost:5432/root";
        String username="postgres";
        String password ="Moraa@2019";

        try{
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            if(connection != null){
                System.out.println("connected to database");
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }
