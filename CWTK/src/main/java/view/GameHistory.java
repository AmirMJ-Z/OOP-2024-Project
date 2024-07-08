package view;

import controller.GameHistoryController;
import controller.GameMenuController;
import controller.MainController;
import model.App;
import model.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameHistory implements Menu{
    private String input;
    private boolean exit;
    private ArrayList<Matcher> matchers;
    private GameHistoryController gameHistoryController = new GameHistoryController();
    private App app = App.getInstance();
    private Scanner s;
    private MainController mainController = new MainController();
    private Database database = Database.getInstance();

    public GameHistory() throws SQLException {
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
        matchers.set(4, App.getCommandMatcher(input, "^\\s*sort\\s+by\\s+date\\s*$"));
        matchers.set(5, App.getCommandMatcher(input, "^\\s*sort\\s+by\\s+result\\s*$"));
        matchers.set(6, App.getCommandMatcher(input, "^\\s*sort\\s+by\\s+level\\s*$"));
    }

    @Override
    public void check() throws SQLException, IOException, ClassNotFoundException {
        if (matchers.get(0).find()) {
            gameHistoryController.back();
            exit = true;
        }

        if (matchers.get(1).find()) {
            System.out.println(gameHistoryController.nextPage());
        }

        if (matchers.get(2).find()) {
            System.out.println(gameHistoryController.previousPage());
        }

        if (matchers.get(3).find()) {
            System.out.println(gameHistoryController.goToPage(Integer.parseInt(matchers.get(3).group("pageNum"))));
        }

        if (matchers.get(4).find()) {
            System.out.println(gameHistoryController.sortByDate());
        }

        if (matchers.get(5).find()) {
            System.out.println(gameHistoryController.sortByResult());
        }

        if (matchers.get(6).find()) {
            System.out.println(gameHistoryController.sortByLevel());
        }
    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n------------\nGAME HISTORY\n------------\n");
        System.out.println(gameHistoryController.showFirstPage());
        run();
    }
}
