package ke.co.safaricom.dao;

import ke.co.safaricom.DB.DB;
import ke.co.safaricom.Models.ItemWithPartnerISP;
import ke.co.safaricom.Models.PartnerISP;
import org.sql2o.Connection;

import java.util.List;

import static ke.co.safaricom.DB.DB.sql2o;

public class Sql2oPartnerISPDao implements PartnerISPDao{

    public static void addPartnerISP(PartnerISP newPartnerISP) {
        try (Connection con = DB.sql2o.beginTransaction()) {
            String sql = "INSERT INTO partnerISPs (partnerName, partnerEmail, description) VALUES (:partnerName, :partnerEmail, :description)";
            con.createQuery(sql)
                    .addParameter("partnerName", newPartnerISP.getPartnerName())
                    .addParameter("partnerEmail", newPartnerISP.getPartnerEmail())
                    .addParameter("description", newPartnerISP.getDescription())
                    .executeUpdate();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public static List<PartnerISP> getAllPartnerISP(){
        String sql = "SELECT * FROM partnerisps";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(PartnerISP.class);
        }
    }
    public static PartnerISP findPartnerISPById(int partnerId) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM partnerisps where partnerId = :partnerId";
            return con.createQuery(sql)
                    .addParameter("partnerId", partnerId)
                    .executeAndFetchFirst(PartnerISP.class);
        }
    }
    public List<ItemWithPartnerISP> getAllPartnersByInventoryItems(int partnerId) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(
                            "SELECT i.itemId, i.itemName, i.itemSerial, i.partnerId, p.partnerName " + // Added space here
                                    "FROM inventory i " +
                                    "INNER JOIN partnerisps p ON i.partnerId = p.partnerId " + // Added space here
                                    "WHERE i.partnerId = :partnerId;")
                    .addParameter("partnerId", partnerId)
                    .executeAndFetch(ItemWithPartnerISP.class);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
    public void updatePartnerISP(PartnerISP partnerISP) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery("UPDATE partnerisps SET partnerName = :partnerName, partnerEmail = :partnerEmail, " +
                            "description = :description " +
                            "WHERE partnerId = :partnerId")
                    .addParameter("partnerName", partnerISP.getPartnerName())
                    .addParameter("partnerEmail", partnerISP.getPartnerEmail())
                    .addParameter("description", partnerISP.getDescription())
                    .addParameter("partnerId", partnerISP.getPartnerId())
                    .executeUpdate();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    public void deletePartnerISPById(int partnerId) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery("DELETE FROM partnerisps WHERE partnerId = :partnerId;")
                    .addParameter("partnerId", partnerId)
                    .executeUpdate();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
