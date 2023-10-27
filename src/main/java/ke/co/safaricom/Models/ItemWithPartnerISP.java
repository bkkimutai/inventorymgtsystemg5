package ke.co.safaricom.Models;

import org.sql2o.Connection;

import java.util.List;

import static ke.co.safaricom.DB.DB.sql2o;

public class ItemWithPartnerISP {
    private int itemId;
    private String itemName;
    private String itemSerial;
    private int partnerId;
    private String partnerName;

    public ItemWithPartnerISP(int itemId, String itemName, String itemSerial, int partnerId, String partnerName) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemSerial = itemSerial;
        this.partnerId = partnerId;
        this.partnerName = partnerName;
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

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public static List<ItemWithPartnerISP> getAllInventoryWithISPs() {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(
                            "SELECT i.itemId, i.itemName, i.itemSerial, i.partnerId, p.partnerName" +
                                    " FROM inventory i " +
                                    "INNER JOIN partnerisps p ON i.partnerId = p.partnerId;")
                    .executeAndFetch(ItemWithPartnerISP.class);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

}
