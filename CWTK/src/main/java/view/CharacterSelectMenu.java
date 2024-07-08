package view;

import controller.CharacterSelectController;
import controller.MainController;
import model.App;
import model.Card.Card;
import model.CardType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class CharacterSelectMenu implements Menu{
    private String input;
    private boolean exit;
    private ArrayList<Matcher> matchers;
    private CharacterSelectController characterSelectController = new CharacterSelectController();
    private App app = App.getInstance();
    private Scanner s;
    private boolean user1Selected = false;
    private boolean user2Selected = false;

    public CharacterSelectMenu() {
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

            if (user1Selected && user2Selected) {
                exit = true;
                characterSelectController.gameLauncher();
            }
        }
    }

    @Override
    public void setMatchers() {
        matchers.set(0, App.getCommandMatcher(input, "^\\s*1\\s*$"));
        matchers.set(1, App.getCommandMatcher(input, "^\\s*2\\s*$"));
        matchers.set(2, App.getCommandMatcher(input, "^\\s*3\\s*$"));
        matchers.set(3, App.getCommandMatcher(input, "^\\s*4\\s*$"));
    }

    @Override
    public void check() throws SQLException, IOException, ClassNotFoundException {

        if (user1Selected) {
            if (matchers.get(0).find()) {
                user2Selected = true;
                System.out.println(characterSelectController.user2Select(1));
            }

            else if (matchers.get(1).find()) {
                user2Selected = true;
                System.out.println(characterSelectController.user2Select(2));
            }

            else if (matchers.get(2).find()) {
                user2Selected = true;
                System.out.println(characterSelectController.user2Select(3));
            }

            else if (matchers.get(3).find()) {
                user2Selected = true;
                System.out.println(characterSelectController.user2Select(4));
            }

            else {
                System.out.println("Please choose a number between 1 and 4");
            }
        }

        else {
            if (matchers.get(0).find()) {
                user1Selected = true;
                System.out.println(characterSelectController.user1Select(1));
            }

            else if (matchers.get(1).find()) {
                user1Selected = true;
                System.out.println(characterSelectController.user1Select(2));
            }

            else if (matchers.get(2).find()) {
                user1Selected = true;
                System.out.println(characterSelectController.user1Select(3));
            }

            else if (matchers.get(3).find()) {
                user1Selected = true;
                System.out.println(characterSelectController.user1Select(4));
            }

            else {
                System.out.println("Please choose a number between 1 and 4");
            }
        }
    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n----------------\nSELECT CHARACTER\n----------------\n");
        System.out.println("1-\t" + CardType.Aldrin);
        System.out.println("2-\t" + CardType.Armstrong);
        System.out.println("3-\t" + CardType.Gagarin);
        System.out.println("4-\t" + CardType.Glenn);
        System.out.println();

        run();
    }
}
