package ke.co.safaricom.Models;

import ke.co.safaricom.DB.DB;
import ke.co.safaricom.DB.DBManagement;
import org.sql2o.Connection;

import java.util.List;

public class InventoryItem implements DBManagement {
    private int itemId;
    private String itemName;
    private String itemSerial;
    private String itemManufacturer;
    private int partnerId;
    public InventoryItem(String itemName, String itemSerial, String itemManufacturer, int partnerId){
        this.itemName=itemName;
        this.itemSerial=itemSerial;
        this.itemManufacturer=itemManufacturer;
        this.partnerId = partnerId;
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

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }
    @Override
    public boolean equals(Object otherInventoryItem) {
        if (!(otherInventoryItem instanceof InventoryItem)) {
            return false;
        } else {
            InventoryItem newInventoryItem = (InventoryItem) otherInventoryItem;
            return this.getItemName().equals(newInventoryItem.getItemName()) &&
                    this.getItemSerial().equals(newInventoryItem.getItemSerial()) &&
                    this.getItemManufacturer().equals(newInventoryItem.getItemManufacturer());
        }
    }
//    @Override
//    public void save() {
//        try (Connection con = DB.sql2o.open()) {
//            String sql = "INSERT INTO Inventory (name, serial, manufacturer, assignedPartnerID) " +
//                    "VALUES (:name, :serial, :manufacturer, :assignedPartnerID)";
//            this.itemId = (int) con.createQuery(sql, true)
//                    .addParameter("name", this.itemName)
//                    .addParameter("serial", this.itemSerial)
//                    .addParameter("manufacturer", this.itemManufacturer)
//                    .addParameter("assignedPartnerID", this.assignedPartnerID)
//                    .executeUpdate()
//                    .getKey();
//        }
//    }
    @Override
    public void save() {
        try (Connection con = DB.sql2o.beginTransaction()) {
            String sql = "INSERT INTO Inventory (itemName, itemSerial, itemManufacturer, partnerId)" +
                    "VALUES (:itemName, :itemSerial, :itemManufacturer, :partnerId)";
            con.createQuery(sql)
                    .addParameter("itemName", this.itemName)
                    .addParameter("itemSerial", this.itemSerial)
                    .addParameter("itemManufacturer", this.itemManufacturer)
                    .addParameter("partnerId", this.partnerId)
                    .executeUpdate();
            String idQuery = "SELECT lastval()";
            this.itemId = con.createQuery(idQuery).executeScalar(Integer.class);

            con.commit();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public static List<InventoryItem> all() {
        String sql = "SELECT * FROM inventory";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(InventoryItem.class);
        }
    }
    public static InventoryItem find(int itemId) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM inventory where itemId = :itemId";
            return con.createQuery(sql)
                    .addParameter("itemId", itemId)
                    .executeAndFetchFirst(InventoryItem.class);
        }
    }
}
