package view.AdminMenu;


import controller.AdminControllers.AdminController;
import controller.MainController;
import model.App;
import model.Database;
import view.Menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class AdminMenu implements Menu {
    private String input;
    private boolean exit;
    private ArrayList<Matcher> matchers;
    private AdminController adminController = new AdminController();
    private MainController mainController = new MainController();
    private App app = App.getInstance();
    private Scanner s;
    private Database database = Database.getInstance();

    public AdminMenu() {
        exit = false;
        matchers = new ArrayList<>();
        s = new Scanner(System.in);
        for (int i=0; i<5 ; i++) {
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
        matchers.set(1, App.getCommandMatcher(input, "^\\s*add\\s+card\\s+-n\\s+(?<name>.+)\\s+-p\\s+(?<point>\\d+)\\s+-d\\s+(?<duration>\\d+)\\s+-a\\s+(?<acc>\\d+)\\s+-l\\s+(?<minlevel>\\d+)\\s*-c\\s+(?<price>\\d+)\\s*$"));
        matchers.set(2, App.getCommandMatcher(input, "^\\s*card\\s+edit\\s+menu\\s*$"));
        matchers.set(3, App.getCommandMatcher(input, "^\\s*card\\s+delete\\s+menu\\s*$"));
        matchers.set(4, App.getCommandMatcher(input, "^\\s*players\\s+menu\\s*$"));

    }

    @Override
    public void check() throws SQLException, IOException, ClassNotFoundException {
        if (matchers.get(0).find()) {
            exit = true;
            mainController.back();
        }

        if (matchers.get(1).find()) {
            Matcher matcher = matchers.get(1);

            System.out.println(adminController.addCard(matcher.group("name"), Integer.parseInt(matcher.group("point")), Integer.parseInt(matcher.group("acc")), Integer.parseInt(matcher.group("duration")), Integer.parseInt(matcher.group("price")), 1, Integer.parseInt(matcher.group("minlevel"))));
        }

        if (matchers.get(2).find()) {
            exit = true;
            adminController.cardEditMenu();
        }

        if (matchers.get(3).find()) {
            exit = true;
            adminController.cardDeleteMenu();
        }

        if (matchers.get(4).find()) {
            exit = true;
            adminController.playersMenu();
        }

    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n----------\nADMIN MENU\n----------");
        run();
    }
}
