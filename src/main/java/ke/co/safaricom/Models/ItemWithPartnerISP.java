package ke.co.safaricom.Models;

import org.sql2o.Connection;

import java.util.List;

import static ke.co.safaricom.DB.DB.sql2o;

public class ItemWithPartnerISP {
    private int itemId;
    private String itemName;
    private String itemSerial;
    private int assignedPartnerID;
    private String name;

    public ItemWithPartnerISP(int itemId, String itemName, String itemSerial, int assignedPartnerID, String name) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemSerial = itemSerial;
        this.assignedPartnerID = assignedPartnerID;
        this.name = name;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSerial() {
        return itemSerial;
    }

    public void setItemSerial(String itemSerial) {
        this.itemSerial = itemSerial;
    }

    public int getAssignedPartnerID() {
        return assignedPartnerID;
    }

    public void setAssignedPartnerID(int partnerId) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<ItemWithPartnerISP> getAllInventoryWithISPs() {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(
                            "SELECT h.itemId, h.itemName, h.itemSerial h.assignedPartnerID, s.name" +
                                    "FROM inventory h " +
                                    "INNER JOIN partnerisps s ON h.assignedPartnerID = s.partnerId;")
                    .executeAndFetch(ItemWithPartnerISP.class);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
