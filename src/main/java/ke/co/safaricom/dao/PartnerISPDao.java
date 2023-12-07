package ke.co.safaricom.dao;

import ke.co.safaricom.Models.ItemWithPartnerISP;
import ke.co.safaricom.Models.PartnerISP;

import java.util.List;

public interface PartnerISPDao {
    //create
    static void addPartnerISP(PartnerISP newPartnerISP) {

    }

    //read
    static List<PartnerISP> getAllPartnerISP(){
        return null;
    };

    static PartnerISP findPartnerISPById(int partnerId) {
        return null;
    }

    List<ItemWithPartnerISP> getAllPartnersByInventoryItems(int partnerId);

    //update
    static void updatePartnerISP(PartnerISP partnerISP){};

    //delete
    void deletePartnerISPById(int partnerId);

}
