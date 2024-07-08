package view;

import controller.LoginController;
import controller.MainController;
import model.App;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements Menu{
    private String input;
    private boolean exit;
    private ArrayList<Matcher> matchers;
    private MainController mainController = new MainController();
    private App app = App.getInstance();
    private Scanner s;

    public MainMenu() {
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
        matchers.set(0, App.getCommandMatcher(input, "^\\s*login\\s+menu\\s*$"));
        matchers.set(1, App.getCommandMatcher(input, "^\\s*signup\\s+menu\\s*$"));
        matchers.set(2, App.getCommandMatcher(input, "^\\s*profile\\s+menu\\s*$"));
        matchers.set(3, App.getCommandMatcher(input, "^\\s*admin\\s+menu\\s*$"));
        matchers.set(4, App.getCommandMatcher(input, "^\\s*score\\s+board\\s*$"));
        matchers.set(5, App.getCommandMatcher(input, "^\\s*game\\s+menu\\s*$"));
    }

    @Override
    public void check() throws SQLException, IOException, ClassNotFoundException {
        if (matchers.get(0).find()) {
            exit = true;
            mainController.loginMenu();
        }

        if (matchers.get(1).find()) {
            exit = true;
            mainController.signUpMenu();
        }

        if (matchers.get(2).find()) {
            exit = true;
            mainController.profileMenu();
        }

        if (matchers.get(3).find()) {
            exit = true;
            mainController.adminMenu();
        }

        if (matchers.get(4).find()) {
            exit = true;
            mainController.scoreBoard();
        }

        if (matchers.get(5).find()) {
            exit = true;
            mainController.gameMenu();
        }
    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n---------\nMAIN MENU\n---------");
        run();
    }
}
