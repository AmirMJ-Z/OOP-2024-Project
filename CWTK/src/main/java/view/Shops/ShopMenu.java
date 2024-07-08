package view.Shops;

import controller.MainController;
import controller.ShopControllers.ShopController;
import model.App;
import model.Database;
import view.Menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu implements Menu {
    private String input;
    private boolean exit;
    private ArrayList<Matcher> matchers;
    private ShopController shopController = new ShopController();
    private App app = App.getInstance();
    private Scanner s;
    private MainController mainController = new MainController();
    private Database database = Database.getInstance();

    public ShopMenu() {
        matchers = new ArrayList<>();
        s = new Scanner(System.in);
        for (int i=0; i<7 ; i++) {
            matchers.add(null);
        }
    }
    @Override
    public void run() throws SQLException, IOException, ClassNotFoundException {
        while(!exit) {
            input = s.nextLine();
            setMatchers();
            check();
        }
    }

    @Override
    public void setMatchers() {
        matchers.set(0, App.getCommandMatcher(input, "^\\s*cards\\s+shop\\s*$"));
        matchers.set(1, App.getCommandMatcher(input, "^\\s*upgrade\\s+shop\\s*$"));
        matchers.set(2, App.getCommandMatcher(input, "^\\s*spell\\s+cards\\s*$"));
        matchers.set(3, App.getCommandMatcher(input, "^\\s*back\\s*$"));
        matchers.set(4, App.getCommandMatcher(input, "^\\s*1\\s*$"));
        matchers.set(5, App.getCommandMatcher(input, "^\\s*2\\s*$"));
        matchers.set(6, App.getCommandMatcher(input, "^\\s*3\\s*$"));

    }

    @Override
    public void check() throws SQLException, IOException, ClassNotFoundException {
        if (matchers.get(0).find() || matchers.get(4).find()) {
            shopController.level1Shop();
        }

        if (matchers.get(1).find() || matchers.get(5).find()) {
            shopController.level2Shop();
        }

        if (matchers.get(2).find() || matchers.get(6).find()) {
            shopController.spellShop();
        }

        if (matchers.get(4).find()) {
            exit = true;
            shopController.back();
        }

    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n---------\nSHOP MENU\n---------\n");
        System.out.println("\n1- Cards Shop\n2- Upgrade Shop\n3- Spell Shop\n");
        shopController.starterPack();
        run();
    }
}
