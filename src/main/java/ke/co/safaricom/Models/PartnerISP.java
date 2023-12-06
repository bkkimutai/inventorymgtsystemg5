package ke.co.safaricom.Models;

import ke.co.safaricom.DB.DB;
import org.sql2o.Connection;

<<<<<<< HEAD
public class   PartnerISP implements DBManagement {
    private String name;
=======
import java.util.List;

public class PartnerISP {
    private String partnerName;
    private String partnerEmail;
>>>>>>> 770e75ab12c7fe868a67a8464fd04475304e4f8f
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

}
