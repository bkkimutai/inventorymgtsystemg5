package ke.co.safaricom.Models;

import ke.co.safaricom.DB.DB;
import ke.co.safaricom.DB.DBManagement;
import org.sql2o.Connection;

public class   PartnerISP implements DBManagement {
    private String name;
    private String description;
    private int partnerId;

    public PartnerISP(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
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
                    this.getDescription().equals(newPartnerISP.getDescription());
        }
    }
    @Override
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO partnerISPs (name, description) VALUES (:name, :description)";
            this.partnerId = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("description", this.description)
                    .executeUpdate()
                    .getKey();
        }
    }
}
