package ke.co.safaricom;
import ke.co.safaricom.Models.InventoryItem;
import ke.co.safaricom.Models.ItemWithPartnerISP;
import ke.co.safaricom.Models.PartnerISP;
import ke.co.safaricom.Models.UserLogin;
import ke.co.safaricom.dao.Sql2oInventoryItemDao;
import ke.co.safaricom.dao.Sql2oPartnerISPDao;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");


        get("/login", (req,res)->{
            Map<String, Object> payload = new HashMap<>();
            return new ModelAndView(payload, "/userLogin.hbs");
        }, new HandlebarsTemplateEngine());



        post("/UserLogin", (req, res) -> {
                    Map<String, Object> payload = new HashMap<>();
                    String email = req.queryParams("email");
                    String password = req.queryParams("password");
                    UserLogin newlogin = new UserLogin();
                    if (newlogin.isValidUser(email, password)) {
                        res.redirect("/");
                    }else  {
                // Invalid login, set error message
                payload.put("error", "Invalid email or password. Please try again.");
            }
            return new ModelAndView(payload, "userLogin.hbs");
        }, new HandlebarsTemplateEngine());


        get("/newUsers", (req,res)->{
            Map<String, Object> payload = new HashMap<>();
            return new ModelAndView(payload, "/createUser.hbs");
        }, new HandlebarsTemplateEngine());



        get("/", (req, res) -> {
            Map<String, Object> payload = new HashMap<>();
            List<ItemWithPartnerISP> InventoryWithISP = ItemWithPartnerISP.getAllInventoryWithISPs();
            payload.put("InventoryWithISP", InventoryWithISP);
            return new ModelAndView(payload, "layout.hbs");
        }, new HandlebarsTemplateEngine());

        get("/inventorylist", (req, res) -> {
            Map<String, Object> payload = new HashMap<>();
            List<ItemWithPartnerISP> InventoryWithISP = ItemWithPartnerISP.getAllInventoryWithISPs();
            payload.put("InventoryWithISP", InventoryWithISP);
            return new ModelAndView(payload, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //show new inventory form
        get("/inventory/new", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
            List<PartnerISP> partners = Sql2oPartnerISPDao.getAllPartnerISP();
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
            Sql2oInventoryItemDao.addInventory(newInventory);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //display form to create a new partner ISP
        get("/partnerisps/new", (request, response) -> {
            Map<String, Object> payload = new HashMap<>();
//            List<Squad> squads = heroSquadDao.getAllSquads();
//            payload.put("squads", squads);
            return new ModelAndView(payload, "new-partnerisp.hbs");
        }, new HandlebarsTemplateEngine());

        //process new Partner
        post("/partnerisps", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String partnerName = request.queryParams("partnerName");
            String partnerEmail = request.queryParams("partnerEmail");
            String description = request.queryParams("description");
            PartnerISP newISP = new PartnerISP(partnerName, partnerEmail, description);
            Sql2oPartnerISPDao.addPartnerISP(newISP);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        //display a single Item from a ISP
        get("/partnerisps/:partnerId/inventories/:itemId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params("itemId"));
            InventoryItem foundItem = Sql2oInventoryItemDao.findInventoryById(itemId);
            model.put("item", foundItem);
            int partnerId = Integer.parseInt(request.params("partnerId"));
            PartnerISP foundPartner = Sql2oPartnerISPDao.findPartnerISPById(partnerId);
            model.put("partnerISP", foundPartner);
            return new ModelAndView(model, "inventory-details.hbs");
        }, new HandlebarsTemplateEngine());
        //Display partnerISP details
        get("/partnerisps/:partnerId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int IspToFindId = Integer.parseInt(request.params("partnerId"));
            PartnerISP foundISP = Sql2oPartnerISPDao.findPartnerISPById(IspToFindId);
            model.put("partnerISP", foundISP);
            List<InventoryItem> allItemsByISP = Sql2oInventoryItemDao.getAllInventoryByPartnerISP(IspToFindId);
            model.put("itemsbyisp", allItemsByISP);
            return new ModelAndView(model, "partner-details.hbs");
        }, new HandlebarsTemplateEngine());

        //Updating/reassigning Inventories
        get("/partnerisps/:partnerId/inventories/:itemId/update-inventory", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(req.params("itemId"));
            InventoryItem foundItem = Sql2oInventoryItemDao.findInventoryById(itemId);
            model.put("item", foundItem);
            int partnerId = Integer.parseInt(req.params("partnerId"));
            PartnerISP foundISP = Sql2oPartnerISPDao.findPartnerISPById(partnerId);
            model.put("partnerISP", foundISP);
            model.put("allPartnerISPs", Sql2oPartnerISPDao.getAllPartnerISP());
            return new ModelAndView(model, "update-inventory.hbs");
        }, new HandlebarsTemplateEngine());

        post("/update-inventory", (req, res) -> {
            Map<String, Object> payload = new HashMap<>();
            String itemName = req.queryParams("itemName");
            String itemSerial = req.queryParams("itemSerial");
            String itemManufacturer = req.queryParams("itemManufacturer");
            int partnerId = Integer.parseInt(req.queryParams("partnerId"));
            InventoryItem updatedInventory = new InventoryItem(itemName, itemSerial,itemManufacturer, partnerId);
            Sql2oInventoryItemDao.updateInventory(updatedInventory);
            res.redirect("/inventorylist");
            return null;
        }, new HandlebarsTemplateEngine());
    }

}