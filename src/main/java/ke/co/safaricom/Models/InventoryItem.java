package ke.co.safaricom.Models;

public class InventoryItem {
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
}
