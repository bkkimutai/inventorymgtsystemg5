package ke.co.safaricom.dao;

import ke.co.safaricom.Models.InventoryItem;
import ke.co.safaricom.Models.PartnerISP;

import java.util.List;

public interface PartnerISPDao {
    //create
    void add (PartnerISP partnerISP);

    //read
    List<PartnerISP> getAll();
    PartnerISP findById(int id);
    List<PartnerISP> getAllPartnersByInventoryItems(int partnerId);

    //update
    void update(int partnerId, String partnerName, String partnerEmail, String description);

    //delete
    void deleteById(int partnerId);

}
