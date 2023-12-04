import ke.co.safaricom.Models.InventoryItem;
import ke.co.safaricom.dao.Sql2oInventoryItemDao;
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
        Sql2oInventoryItemDao.addInventory(testItem);
        assertEquals(Sql2oInventoryItemDao.getAllInventory().get(0), testItem);
    }

    @Test
    public void all_returnsAllInstancesOfItemss_true() {
        InventoryItem firstItem = new InventoryItem("MWRadio", "AAAAA", "Radwin", 1);
        Sql2oInventoryItemDao.addInventory(firstItem);
        InventoryItem secondItem = new InventoryItem("AR169", "BBBBB", "Huawei", 2);
        Sql2oInventoryItemDao.addInventory(secondItem);
        List<InventoryItem> items = Sql2oInventoryItemDao.getAllInventory();
        assertTrue(items.contains(firstItem));
        assertTrue(items.contains(secondItem));
    }
    @Test
    public void save_assignsIdToObject() {
        InventoryItem testItem = new InventoryItem("AR169", "BBBBB", "Huawei", 2);
        Sql2oInventoryItemDao.addInventory(testItem);
        List<InventoryItem> savedItem = Sql2oInventoryItemDao.getAllInventory();
        assertEquals(1, savedItem.get(0).getItemId());
    }
    @Test
    public void find_returnsItemsWithSameId_secondItem() {
        InventoryItem firstItem = new InventoryItem("MWRadio", "AAAAA", "Radwin", 1);
        Sql2oInventoryItemDao.addInventory(firstItem);
        InventoryItem secondItem = new InventoryItem("AR169", "BBBBB", "Huawei", 2);
        Sql2oInventoryItemDao.addInventory(secondItem);
        assertEquals(Sql2oInventoryItemDao.findInventoryById(firstItem.getItemId()), firstItem);
    }
}
