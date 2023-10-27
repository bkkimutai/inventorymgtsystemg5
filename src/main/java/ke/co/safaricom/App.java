package ke.co.safaricom;
import ke.co.safaricom.Models.InventoryItem;
import ke.co.safaricom.Models.ItemWithPartnerISP;
import ke.co.safaricom.Models.PartnerISP;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (req, res) -> {
            Map<String, Object> payload = new HashMap<>();
            List<ItemWithPartnerISP> InventoryWithISP = ItemWithPartnerISP.getAllInventoryWithISPs();
            payload.put("InventoryWithISP", InventoryWithISP);
            return new ModelAndView(payload, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //show new inventory form
        get("/inventory/new", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            List<PartnerISP> partners = PartnerISP.all();
            payload.put("partnerisps", partners);
            return new ModelAndView(payload, "new-item.hbs");
        }, new HandlebarsTemplateEngine());

        //Post new Inventory into DB
        post("/inventories", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            String itemName = request.queryParams("itemName");
            String itemSerial = request.queryParams("itemSerial");
            String itemManufacturer = request.queryParams("itemManufacturer");
            int partnerId = Integer.parseInt(request.queryParams("partnerId"));
            InventoryItem newInventory = new InventoryItem(itemName, itemSerial, itemManufacturer, partnerId);
            newInventory.save();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

    }

}