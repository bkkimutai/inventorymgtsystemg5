import ke.co.safaricom.Models.InventoryItem;
import ke.co.safaricom.Models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryItemTests {

    private static final Sql2o testSql2o = new Sql2o("jdbc:postgresql://localhost:5432/inventorymgt_test", "postgres", "Msama@012023");

    @RegisterExtension
    public static final DatabaseExtension databaseExtension = new DatabaseExtension(testSql2o);
    @Test
    public void Item_instantiatesCorrectly_true() {
        InventoryItem testItem = new InventoryItem ("MW Radio", "AAAAA", "Radwin", 1);
        assertTrue(true);
    }
    @Test
    public void getName_ItemInstantiatesWithName_MWRadio() {
        InventoryItem testItem = new InventoryItem("MWRadio", "AAAAA", "Radwin", 1);
        assertEquals("MWRadio", testItem.getItemName());
    }
    @Test
    public void getSerial_userInstantiatesWithSerial_String() {
        InventoryItem testItem = new InventoryItem("MWRadio", "AAAAA", "Radwin", 1);
        assertEquals("AAAAA", testItem.getItemSerial());
    }
    @Test
    public void equals_returnsTrueIfNameSerialAndManufacturerAreSame_true() {
        InventoryItem firstItem = new InventoryItem("MWRadio", "AAAAA", "Radwin", 1);
        InventoryItem anotherItem = new InventoryItem("MWRadio", "AAAAA", "Radwin", 1);
        assertEquals(firstItem, anotherItem);
    }
    @Test
    public void save_insertsObjectIntoDatabase_User() {
        InventoryItem testItem = new InventoryItem("MWRadio", "AAAAA", "Radwin", 1);
        testItem.save();
        assertEquals(InventoryItem.all().get(0), testItem);
    }

    @Test
    public void all_returnsAllInstancesOfItemss_true() {
        InventoryItem firstItem = new InventoryItem("MWRadio", "AAAAA", "Radwin", 1);
        firstItem.save();
        InventoryItem secondItem = new InventoryItem("AR169", "BBBBB", "Huawei", 2);
        secondItem.save();
        List<InventoryItem> items = InventoryItem.all();
        assertTrue(items.contains(firstItem));
        assertTrue(items.contains(secondItem));
    }
    @Test
    public void save_assignsIdToObject() {
        InventoryItem testItem = new InventoryItem("AR169", "BBBBB", "Huawei", 2);
        testItem.save();
        List<InventoryItem> savedItem = InventoryItem.all();
        assertEquals(1, savedItem.get(0).getItemId());
    }
    @Test
    public void find_returnsItemsWithSameId_secondItem() {
        InventoryItem firstItem = new InventoryItem("MWRadio", "AAAAA", "Radwin", 1);
        firstItem.save();
        InventoryItem secondItem = new InventoryItem("AR169", "BBBBB", "Huawei", 2);
        secondItem.save();
        assertEquals(InventoryItem.find(firstItem.getItemId()), firstItem);
    }
}
