package view.Shops;

import controller.MainController;
import controller.ShopControllers.CardShopController;
import model.App;
import model.Database;
import view.Menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class CardShop implements Menu {
    private String input;
    private boolean exit;
    private ArrayList<Matcher> matchers;
    private CardShopController shopController = new CardShopController();
    private App app = App.getInstance();
    private Scanner s;
    private MainController mainController = new MainController();
    private Database database = Database.getInstance();

    public CardShop() throws SQLException {
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
        matchers.set(0, App.getCommandMatcher(input, "^\\s*back\\s*$"));
        matchers.set(1, App.getCommandMatcher(input, "^\\s*next\\s*$"));
        matchers.set(2, App.getCommandMatcher(input, "^\\s*previous\\s*$"));
        matchers.set(3, App.getCommandMatcher(input, "^\\s*page\\s+-n\\s+(?<pageNum>\\d+)\\s*$"));
        matchers.set(4, App.getCommandMatcher(input, "^\\s*buy\\s+(?<index>\\d+)\\s*$"));

    }

    @Override
    public void check() throws SQLException, IOException, ClassNotFoundException {
        if (matchers.get(0).find()) {
            exit = true;
            shopController.back();
        }

        if (matchers.get(1).find()) {
            System.out.println(shopController.nextPage());
        }

        if (matchers.get(2).find()) {
            System.out.println(shopController.previousPage());
        }

        if (matchers.get(3).find()) {
            System.out.println(shopController.goToPage(Integer.parseInt(matchers.get(3).group("pageNum"))));
        }

        if (matchers.get(4).find()) {
            System.out.println(shopController.buy(Integer.parseInt(matchers.get(4).group("index"))));
            System.out.println(shopController.showFirstPage());
        }

    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n---------\nCARD SHOP\n---------\n");
        System.out.println(shopController.showFirstPage());
        run();

    }
}
