package view.AdminMenu;

import controller.AdminControllers.CardDeleteController;
import controller.AdminControllers.PlayersController;
import controller.MainController;
import model.App;
import model.Database;
import view.Menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class PlayersMenu implements Menu {
    private String input;
    private boolean exit;
    private ArrayList<Matcher> matchers;
    private PlayersController playersController = new PlayersController();
    private MainController mainController = new MainController();
    private App app = App.getInstance();
    private Scanner s;
    private Database database = Database.getInstance();

    public PlayersMenu() throws SQLException {
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

    }

    @Override
    public void check() throws SQLException, IOException, ClassNotFoundException {
        if (matchers.get(0).find()) {
            exit = true;
            playersController.back();
        }

        if (matchers.get(1).find()) {
            System.out.println(playersController.nextPage());
        }

        if (matchers.get(2).find()) {
            System.out.println(playersController.previousPage());
        }

        if (matchers.get(3).find()) {
            System.out.println(playersController.goToPage(Integer.parseInt(matchers.get(3).group("pageNum"))));
        }

    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n-------\nPLAYERS\n-------\n");
        System.out.println(playersController.showFirstPage());
        run();
    }
}
