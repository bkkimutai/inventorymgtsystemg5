package ke.co.safaricom.Models;

import ke.co.safaricom.DB.DB;
import ke.co.safaricom.DB.DBManagement;
import org.sql2o.Connection;

public class inventoryItem implements DBManagement {
    private int itemId;
    private String itemName;
    private String itemSerial;
    private String itemManufacturer;
    private String assignedPartnerID;
    public inventoryItem(String itemName,String itemSerial,String itemManufacturer,String assignedPartnerID){
        this.itemName=itemName;
        this.itemSerial=itemSerial;
        this.itemManufacturer=itemManufacturer;
        this.assignedPartnerID=assignedPartnerID;
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

    public String getItemManufacturer() {
        return itemManufacturer;
    }

    public void setItemManufacturer(String itemManufacturer) {
        this.itemManufacturer = itemManufacturer;
    }

    public String getAssignedPartnerID() {
        return assignedPartnerID;
    }

    public void setAssignedPartnerID(String assignedPartnerID) {
        this.assignedPartnerID = assignedPartnerID;
    }
    @Override
    public boolean equals(Object otherInventoryItem) {
        if (!(otherInventoryItem instanceof inventoryItem)) {
            return false;
        } else {
            inventoryItem newInventoryItem = (inventoryItem) otherInventoryItem;
            return this.getItemName().equals(newInventoryItem.getItemName()) &&
                    this.getItemSerial().equals(newInventoryItem.getItemSerial());
        }
    }
    @Override
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO Inventory (name, serial, manufacturer, assignedPartnerID) " +
                    "VALUES (:name, :serial, :manufacturer, :assignedPartnerID)";
            this.itemId = (int) con.createQuery(sql, true)
                    .addParameter("name", this.itemName)
                    .addParameter("serial", this.itemSerial)
                    .addParameter("manufacturer", this.itemManufacturer)
                    .addParameter("assignedPartnerID", this.assignedPartnerID)
                    .executeUpdate()
                    .getKey();
        }
    }
}
