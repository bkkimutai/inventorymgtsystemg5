package ke.co.safaricom.Models;

import ke.co.safaricom.DB.DB;
import org.sql2o.Connection;
import java.util.List;

public class PartnerISP {
    private String partnerName;
    private String partnerEmail;
    private String description;
    private int partnerId;


        public PartnerISP(String partnerName, String partnerEmail, String description) {
            this.partnerName = partnerName;
            this.partnerEmail = partnerEmail;
            this.description = description;
        }

        @Override
        public boolean equals(Object otherPartnerISP) {
            if (!(otherPartnerISP instanceof PartnerISP)) {
                return false;
            } else {
                PartnerISP newPartnerISP = (PartnerISP) otherPartnerISP;
                return this.getPartnerName().equals(newPartnerISP.getPartnerName()) &&
                        this.getDescription().equals(newPartnerISP.getDescription()) &&
                        this.getPartnerEmail().equals(newPartnerISP.getPartnerEmail());
            }
        }

        public String getPartnerName() {
            return partnerName;
        }

        public void setPartnerName(String partnerName) {
            this.partnerName = partnerName;
        }

        public String getPartnerEmail() {
            return partnerEmail;
        }

        public void setPartnerEmail(String partnerEmail) {
            this.partnerEmail = partnerEmail;
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

    }
