package view;

import controller.GameHistoryController;
import controller.MainController;
import controller.ScoreBoardController;
import model.App;
import model.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ScoreBoard implements Menu{
    private String input;
    private boolean exit;
    private ArrayList<Matcher> matchers;
    private ScoreBoardController scoreBoardController = new ScoreBoardController();
    private App app = App.getInstance();
    private Scanner s;
    private MainController mainController = new MainController();
    private Database database = Database.getInstance();

    public ScoreBoard() throws SQLException {
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
        matchers.set(4, App.getCommandMatcher(input, "^\\s*sort\\s+by\\s+level\\s*$"));
        matchers.set(5, App.getCommandMatcher(input, "^\\s*sort\\s+by\\s+name\\s*$"));
        matchers.set(6, App.getCommandMatcher(input, "^\\s*sort\\s+by\\s+coin\\s*$"));

    }

    @Override
    public void check() throws SQLException, IOException, ClassNotFoundException {
        if (matchers.get(0).find()) {
            scoreBoardController.back();
            exit = true;
        }

        if (matchers.get(1).find()) {
            System.out.println(scoreBoardController.nextPage());
        }

        if (matchers.get(2).find()) {
            System.out.println(scoreBoardController.previousPage());
        }

        if (matchers.get(3).find()) {
            System.out.println(scoreBoardController.goToPage(Integer.parseInt(matchers.get(3).group("pageNum"))));
        }

        if (matchers.get(4).find()) {
            System.out.println(scoreBoardController.sortByLevel());
        }

        if (matchers.get(5).find()) {
            System.out.println(scoreBoardController.sortByName());
        }

        if (matchers.get(6).find()) {
            System.out.println(scoreBoardController.sortByCoin());
        }

    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n-----------\nSCORE BOARD\n-----------\n");
        System.out.println(scoreBoardController.showFirstPage());
        run();
    }
}
