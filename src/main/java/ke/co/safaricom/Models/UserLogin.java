package ke.co.safaricom.Models;
import java.sql.*;
import ke.co.safaricom.DB.DB;
import spark.Spark;

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

    public boolean isValidUser(String email, String password){
        String query = "SELECT * FROM users WHERE email=? AND password=?";
        return false;
    }
public static void main(String[] args){
        Spark.post("/login",(req, res)->{
            String email =req.queryParams("email");
            String password =req.queryParams("password");
            UserLogin loginService = new UserLogin();
            if (loginService.isValidUser(email,password)){
                res.redirect("/");
            }
            return null;
        });
}
}
