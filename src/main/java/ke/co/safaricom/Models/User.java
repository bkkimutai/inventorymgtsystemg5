package ke.co.safaricom.Models;

import ke.co.safaricom.DB.DB;
import ke.co.safaricom.DB.DBManagement;
import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class User implements DBManagement {
    private String name;
    private String email;
    String userType;
    private int userId;


    public User(String name, String email, String userType) {
        this.name = name;
        this.email = email;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object otherUser){
        if (!(otherUser instanceof User)) {
            return false;
        } else {
            User newUser= (User) otherUser;
            return this.getName().equals(newUser.getName()) &&
                    this.getEmail().equals(newUser.getEmail()) &&
                    this.getUserType().equals(newUser.getUserType());
        }
    }

//    @Override
//    public void save() {
//        try (Connection con = DB.sql2o.beginTransaction()) {
//            String sql = "INSERT INTO users (name, email, userType) VALUES (:name, :email, :userType)";
//            this.userId = (int) con.createQuery(sql, true)
//                    .addParameter("name", this.name)
//                    .addParameter("email", this.email)
//                    .addParameter("userType", this.userType)
//                    .executeUpdate()
//                    .getKey();
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        }
//    }
    @Override
    public void save() {
        try (Connection con = DB.sql2o.beginTransaction()) {
            String sql = "INSERT INTO users (name, email, userType) VALUES (:name, :email, :userType)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("email", this.email)
                    .addParameter("userType", this.userType)
                    .executeUpdate();
            String idQuery = "SELECT lastval()";
            this.userId = con.createQuery(idQuery).executeScalar(Integer.class);

            con.commit();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static List<User> all() {
        String sql = "SELECT * FROM users";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(User.class);
        }
    }
    public static User find(int userId) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM users where userId = :userId";
            return con.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeAndFetchFirst(User.class);
        }
    }
}
