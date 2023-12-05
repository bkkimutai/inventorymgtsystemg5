package ke.co.safaricom.dao;

import ke.co.safaricom.Models.InventoryItem;
import ke.co.safaricom.Models.ItemWithPartnerISP;
import ke.co.safaricom.Models.PartnerISP;

import java.util.List;

public interface InventoryItemDao {
    //create
    static void addInventory(InventoryItem newInventory){};

    //read
    static List<InventoryItem> getAllInventory() {
        return null;
    }

    static InventoryItem findInventoryById(int itemId) {
        return null;
    }

    List<ItemWithPartnerISP> getAllInventoryByPartnerISP(int partnerId);

    //update
    void updateInventory(InventoryItem inventoryItem);

    //delete
    void deleteInventoryById(int itemId);
}
