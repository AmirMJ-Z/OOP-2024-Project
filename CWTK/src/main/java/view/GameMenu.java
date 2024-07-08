package view;

import controller.GameMenuController;
import controller.LoginController;
import controller.MainController;
import model.App;
import model.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements Menu {
    private String input;
    private boolean exit;
    private ArrayList<Matcher> matchers;
    private GameMenuController gameMenuController = new GameMenuController();
    private App app = App.getInstance();
    private Scanner s;
    private MainController mainController = new MainController();
    private Database database = Database.getInstance();

    public GameMenu() {
        exit = false;
        matchers = new ArrayList<>();
        s = new Scanner(System.in);
        for (int i=0; i<6 ; i++) {
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
        matchers.set(0, App.getCommandMatcher(input, "^\\s*game\\s+history\\s*$"));
        matchers.set(1, App.getCommandMatcher(input, "^\\s*shop\\s*$"));
        matchers.set(2, App.getCommandMatcher(input, "^\\s*play\\s*$"));
        matchers.set(3, App.getCommandMatcher(input, "^\\s*show\\s+cards\\s*$"));
        matchers.set(4, App.getCommandMatcher(input, "^\\s*back\\s*$"));

    }

    @Override
    public void check() throws SQLException, IOException, ClassNotFoundException {
        if (matchers.get(0).find()) {
            exit = true;
            gameMenuController.gameHistory();
        }

        if (matchers.get(1).find()) {
            exit = true;
            gameMenuController.shopMenu();
        }

        if (matchers.get(2).find()) {
            exit = true;
            gameMenuController.playMenu();
        }

        if (matchers.get(3).find()) {
            exit = true;
            System.out.println(gameMenuController.showCards());

        }

        if (matchers.get(4).find()) {
            mainController.back();
        }

    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        if (app.getCurrentUser() == null) {
            System.out.println("There is no user logged into the database. Please use the login or signup menu instead");
            MainMenu mainMenu = new MainMenu();
//            app.setCurrentMenu(mainMenu());
            mainMenu.start();
            return;
        }

        System.out.println("\n---------\nGAME MENU\n---------");
        run();
    }
}
