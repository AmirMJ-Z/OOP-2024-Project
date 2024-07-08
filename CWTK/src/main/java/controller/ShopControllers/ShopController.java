package controller.ShopControllers;

import model.App;
import model.Database;
import view.GameMenu;
import view.Shops.CardShop;
import view.Shops.UpgradeShop;
import view.Shops.SpellShop;

import java.io.IOException;
import java.sql.SQLException;

public class ShopController {
    private App app = App.getInstance();
    private Database database = Database.getInstance();

    public void level1Shop() throws SQLException, IOException, ClassNotFoundException {
        CardShop cardShop = new CardShop();
//        App.getInstance().setCurrentMenu(level1Shop);
        cardShop.start();
    }

    public void level2Shop() throws SQLException, IOException, ClassNotFoundException {
        UpgradeShop upgradeShop = new UpgradeShop();
//        App.getInstance().setCurrentMenu(level2Shop);
        upgradeShop.start();
    }

    public void spellShop() throws SQLException, IOException, ClassNotFoundException {
        SpellShop spellShop = new SpellShop();
//        App.getInstance().setCurrentMenu(spellShop);
        spellShop.start();
    }

    public String starterPack() throws SQLException {
        if (app.getCurrentUser().hasReceivedStarterPack()) {
            return "";
        }

        app.getCurrentUser().getCardLibrary().starterPack();
        database.updateUser(app.getCurrentUser());
        return "\n------------------------------\nYou received the starter pack!\n------------------------------\n";
    }

    public void back() throws SQLException, IOException, ClassNotFoundException {
        GameMenu gameMenu = new GameMenu();
//        app.setCurrentMenu(gameMenu);
        gameMenu.start();
    }
}
