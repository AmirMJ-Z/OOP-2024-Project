package view;

import controller.GameMenuController;
import controller.MainController;
import controller.PlayMenuController;
import model.App;
import model.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class PlayMenu implements Menu {
    private String input;
    private boolean exit;
    private ArrayList<Matcher> matchers;
    private PlayMenuController playMenuController = new PlayMenuController();
    private App app = App.getInstance();
    private Scanner s;
    private MainController mainController = new MainController();
    private Database database = Database.getInstance();

    public PlayMenu() {
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
        matchers.set(0, App.getCommandMatcher(input, "^\\s*casual\\s*$"));
        matchers.set(1, App.getCommandMatcher(input, "^\\s*gambling\\s*$"));

    }

    @Override
    public void check() throws SQLException, IOException, ClassNotFoundException {
        if (matchers.get(0).find()) {
            exit = true;
            playMenuController.casual();
        }

        if (matchers.get(1).find()) {

        }
    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n---------\nPLAY MENU\n---------\n");
        run();
    }
}
