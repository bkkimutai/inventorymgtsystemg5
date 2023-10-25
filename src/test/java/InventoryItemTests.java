import ke.co.safaricom.Models.InventoryItem;
import ke.co.safaricom.Models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryItemTests {

    private static final Sql2o testSql2o = new Sql2o("jdbc:postgresql://localhost:5432/inventorymgt_test", "postgres", "Msama@012023");

    @RegisterExtension
    public static final DatabaseExtension databaseExtension = new DatabaseExtension(testSql2o);
    @Test
    public void Item_instantiatesCorrectly_true() {
        InventoryItem testItem = new InventoryItem ("MW Radio", "AAAAA", "Radwin", "Broadband");
        assertTrue(true);
    }
    @Test
    public void getName_ItemInstantiatesWithName_MWRadio() {
        InventoryItem testItem = new InventoryItem("MWRadio", "AAAAA", "Radwin", "Broadband");
        assertEquals("MWRadio", testItem.getItemName());
    }
    @Test
    public void getSerial_userInstantiatesWithSerial_String() {
        InventoryItem testItem = new InventoryItem("MWRadio", "AAAAA", "Radwin", "Broadband");
        assertEquals("AAAAA", testItem.getItemSerial());
    }
    @Test
    public void equals_returnsTrueIfNameSerialAndManufacturerAreSame_true() {
        InventoryItem firstItem = new InventoryItem("MWRadio", "AAAAA", "Radwin", "Broadband");
        InventoryItem anotherItem = new InventoryItem("MWRadio", "AAAAA", "Radwin", "Broadband");
        assertEquals(firstItem, anotherItem);
    }
    @Test
    public void save_insertsObjectIntoDatabase_User() {
        InventoryItem testItem = new InventoryItem("MWRadio", "AAAAA", "Radwin", "Broadband");
        testItem.save();
        assertEquals(InventoryItem.all().get(0), testItem);
    }

}
