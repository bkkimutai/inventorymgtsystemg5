import ke.co.safaricom.Models.PartnerISP;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PartnerISPTests {
    private static final Sql2o testSql2o = new Sql2o("jdbc:postgresql://localhost:5432/inventorymgt_test", "postgres", "Msama@012023");

    @RegisterExtension
    public static final DatabaseExtension databaseExtension = new DatabaseExtension(testSql2o);

    @Test
    public void user_instantiatesCorrectly_true() {
        PartnerISP testISP = new PartnerISP ("Broadband", "Nairobi East and Environs", "email@broadbanc.com");
        assertEquals(true, testISP instanceof PartnerISP);
    }
    @Test
    public void getName_ispInstantiatesWithName_Broadband() {
        PartnerISP testISP = new PartnerISP("Broadband", "Nairobi East and Environs", "email@broadbanc.com");
        assertEquals("Broadband", testISP.getPartnerName());
    }
    @Test
    public void getEmail_ISPInstantiatesWithEmail_String() {
        PartnerISP testISP = new PartnerISP("Broadband", "Nairobi East and Environs", "email@broadbanc.com");
        assertEquals("email@broadbanc.com", testISP.getPartnerEmail());
    }
    @Test
    public void equals_returnsTrueIfNameAndEmailAreSame_true() {
        PartnerISP firstISP = new PartnerISP("Broadband", "Nairobi East and Environs", "email@broadbanc.com");
        PartnerISP secondISP = new PartnerISP("Broadband", "Nairobi East and Environs", "email@broadbanc.com");
        assertEquals(firstISP, secondISP);
    }
    @Test
    public void save_insertsObjectIntoDatabase_PartnerISP() {
        PartnerISP testISP = new PartnerISP("Broadband", "Nairobi East and Environs", "email@broadbanc.com");
        testISP.save();
        assertEquals(PartnerISP.all().get(0), testISP);
    }
    @Test
    public void all_returnsAllInstancesOfPartnerISPs_true() {
        PartnerISP firstISP = new PartnerISP("Broadband", "Nairobi East and Environs", "email@broadbanc.com");
        firstISP.save();
        PartnerISP secondISP = new PartnerISP("Broadband", "Nairobi East and Environs", "email@broadbanc.com");
        secondISP.save();
        List<PartnerISP> IPS = PartnerISP.all();

        assertTrue(IPS.contains(firstISP));
        assertTrue(IPS.contains(secondISP));
    }
    @Test
    public void save_assignsIdToObject() {
        PartnerISP firstISP = new PartnerISP("Broadband", "Nairobi East and Environs", "email@broadbanc.com");
        firstISP.save();
        List<PartnerISP> savedISP= PartnerISP.all();
        assertEquals(1, savedISP.get(0).getPartnerId());
    }
    @Test
    public void find_returnsISPsWithSameId_secondISP() {
        PartnerISP firstISP = new PartnerISP("Broadband", "Nairobi East and Environs", "email@broadbanc.com");
        firstISP.save();
        PartnerISP secondISP = new PartnerISP("Broadband", "Nairobi East and Environs", "email@broadbanc.com");
        secondISP.save();
        assertEquals(PartnerISP.find(firstISP.getPartnerId()), firstISP);
    }
}
