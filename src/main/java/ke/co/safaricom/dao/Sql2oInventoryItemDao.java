package ke.co.safaricom.dao;

import ke.co.safaricom.DB.DB;
import ke.co.safaricom.Models.InventoryItem;
import ke.co.safaricom.Models.ItemWithPartnerISP;
import org.sql2o.Connection;

import java.util.List;

import static ke.co.safaricom.DB.DB.sql2o;

public class Sql2oInventoryItemDao implements InventoryItemDao{
    public static void addInventory(InventoryItem newInventory){
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO inventory (itemName, itemSerial, itemManufacturer, partnerId) " +
                    "VALUES (:itemName, :itemSerial, :itemManufacturer, :partnerId)";
            con.createQuery(sql)
                    .addParameter("itemName", newInventory.getItemName())
                    .addParameter("itemSerial", newInventory.getItemSerial())
                    .addParameter("itemManufacturer", newInventory.getItemManufacturer())
                    .addParameter("partnerId", newInventory.getPartnerId())
                    .executeUpdate();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public static List<InventoryItem> getAllInventory() {
        String sql = "SELECT * FROM inventory";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(InventoryItem.class);
        }
    }
    public static InventoryItem findInventoryById(int itemId) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM inventory where itemId = :itemId";
            return con.createQuery(sql)
                    .addParameter("itemId", itemId)
                    .executeAndFetchFirst(InventoryItem.class);
        }
    }
    public static List<InventoryItem> getAllInventoryByPartnerISP(int partnerId) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery("SELECT * FROM inventory WHERE partnerId = :partnerId")
                    .addParameter("partnerId", partnerId)
                    .executeAndFetch(InventoryItem.class);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null; // Handle exceptions appropriately
        }
    }

    public static void updateInventory(InventoryItem updatedInventory) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery("UPDATE inventory SET itemName = :itemName, itemSerial = :itemSerial, " +
                            "itemManufacturer = :itemManufacturer, partnerId = :partnerId " +
                            "WHERE itemSerial = :itemSerial")
                    .addParameter("itemName", updatedInventory.getItemName())
                    .addParameter("itemSerial", updatedInventory.getItemSerial())
                    .addParameter("itemManufacturer", updatedInventory.getItemManufacturer())
                    .addParameter("partnerId", updatedInventory.getPartnerId())
                    .executeUpdate();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void deleteInventoryById(int itemId) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery("DELETE FROM Inventory WHERE itemId = :itemId;")
                    .addParameter("itemId", itemId)
                    .executeUpdate();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
