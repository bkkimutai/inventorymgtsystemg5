package ke.co.safaricom.Models;

import ke.co.safaricom.DB.DB;
import ke.co.safaricom.DB.DBManagement;
import org.sql2o.Connection;

import java.util.List;

public class PartnerISP implements DBManagement {
    private String partnerName;
    private String partnerEmail;
    private String description;
    private int partnerId;

    public PartnerISP(String partnerName, String description, String partnerEmail) {
        this.partnerName = partnerName;
        this.description = description;
        this.partnerEmail = partnerEmail;
    }

    public String getPartnerName() {
        return partnerName;
    }
    public String getPartnerEmail(){
        return partnerEmail;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
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
            return this.getPartnerName().equals(newPartnerISP.getPartnerName()) &&
                    this.getDescription().equals(newPartnerISP.getDescription())&&
                    this.getPartnerEmail().equals(newPartnerISP.getPartnerEmail());
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
            String sql = "INSERT INTO partnerISPs (partnerName, partnerEmail, description) VALUES (:partnerName, :partnerEmail, :description)";
            con.createQuery(sql)
                    .addParameter("partnerName", this.partnerName)
                    .addParameter("partnerEmail", this.partnerEmail)
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
