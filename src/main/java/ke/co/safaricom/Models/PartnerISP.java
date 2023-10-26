package ke.co.safaricom.Models;

import ke.co.safaricom.DB.DB;
import ke.co.safaricom.DB.DBManagement;
import org.sql2o.Connection;

import java.util.List;

public class PartnerISP implements DBManagement {
    private String name;
    private String email;
    private String description;
    private int partnerId;

    public PartnerISP(String name, String description, String email) {
        this.name = name;
        this.description = description;
        this.email =email;
    }

    public String getName() {
        return name;
    }
    public String getEmail(){
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }
    @Override
    public boolean equals(Object otherPartnerISP){
        if (!(otherPartnerISP instanceof PartnerISP)) {
            return false;
        } else {
            PartnerISP newPartnerISP = (PartnerISP) otherPartnerISP;
            return this.getName().equals(newPartnerISP.getName()) &&
                    this.getDescription().equals(newPartnerISP.getDescription())&&
                    this.getEmail().equals(newPartnerISP.getEmail());
        }
    }
//    @Override
//    public void save() {
//        try(Connection con = DB.sql2o.open()) {
//            String sql = "INSERT INTO partnerISPs (name, description) VALUES (:name, :description)";
//            this.partnerId = (int) con.createQuery(sql, true)
//                    .addParameter("name", this.name)
//                    .addParameter("description", this.description)
//                    .executeUpdate()
//                    .getKey();
//        }
//    }
    @Override
    public void save() {
        try (Connection con = DB.sql2o.beginTransaction()) {
            String sql = "INSERT INTO partnerISPs (name, email, description) VALUES (:name, :email, :description)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("email", this.email)
                    .addParameter("description", this.description)
                    .executeUpdate();
            String idQuery = "SELECT lastval()";
            this.partnerId = con.createQuery(idQuery).executeScalar(Integer.class);

            con.commit();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public static List<PartnerISP> all() {
        String sql = "SELECT * FROM partnerisps";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(PartnerISP.class);
        }
    }
    public static PartnerISP find(int partnerId) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM partnerisps where partnerId = :partnerId";
            return con.createQuery(sql)
                    .addParameter("partnerId", partnerId)
                    .executeAndFetchFirst(PartnerISP.class);
        }
    }
}
