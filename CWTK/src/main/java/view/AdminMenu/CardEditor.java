package view.AdminMenu;

import controller.AdminControllers.CardEditController;
import controller.AdminControllers.CardEditorController;
import controller.MainController;
import model.App;
import model.Card.Card;
import model.Database;
import view.Menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class CardEditor implements Menu {
    private Card card;
    private Card tempCard;
    private String input;
    private boolean exit;
    private ArrayList<Matcher> matchers;
    private CardEditorController cardEditorController;
    private MainController mainController = new MainController();
    private App app = App.getInstance();
    private Scanner s;
    private Database database = Database.getInstance();
    public CardEditor(Card card) {
        this.card = card;
        this.tempCard = card.clone();
        cardEditorController = new CardEditorController(tempCard);

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
        matchers.set(0, App.getCommandMatcher(input, "^\\s*save\\s*$"));
        matchers.set(1, App.getCommandMatcher(input, "^\\s*edit\\s+name\\s+(?<name>.+)\\s*$"));
        matchers.set(2, App.getCommandMatcher(input, "^\\s*edit\\s+point\\s+(?<point>\\d+)\\s*$"));
        matchers.set(3, App.getCommandMatcher(input, "^\\s*edit\\s+acc\\s+(?<acc>\\d+)\\s*$"));
        matchers.set(4, App.getCommandMatcher(input, "^\\s*edit\\s+duration\\s+(?<duration>\\d+)\\s*$"));
        matchers.set(5, App.getCommandMatcher(input, "^\\s*edit\\s+price\\s+(?<price>\\d+)\\s*$"));
        matchers.set(6, App.getCommandMatcher(input, "^\\s*edit\\s+upgrade\\s+level\\s+(?<minlevel>\\d+)\\s*$"));


    }

    @Override
    public void check() throws SQLException, IOException, ClassNotFoundException {
        if (matchers.get(0).find()) {
            exit = true;
            cardEditorController.save();
        }

        if (matchers.get(1).find()) {
            System.out.println(cardEditorController.changeName(matchers.get(1).group("name")));
            System.out.println(card.toString());
        }

        if (matchers.get(2).find()) {
            System.out.println(cardEditorController.changeScore(Integer.parseInt(matchers.get(2).group("point"))));
            System.out.println(card.toString());
        }

        if (matchers.get(3).find()) {
            System.out.println(cardEditorController.changeACC(Integer.parseInt(matchers.get(3).group("acc"))));
            System.out.println(card.toString());
        }

        if (matchers.get(4).find()) {
            System.out.println(cardEditorController.changeDuration(Integer.parseInt(matchers.get(4).group("duration"))));
            System.out.println(card.toString());
        }

        if (matchers.get(5).find()) {
            System.out.println(cardEditorController.changePrice(Integer.parseInt(matchers.get(5).group("price"))));
            System.out.println(card.toString());
        }

        if (matchers.get(6).find()) {
            System.out.println(cardEditorController.changeMinLevel(Integer.parseInt(matchers.get(6).group("minlevel"))));
            System.out.println(card.toString());
        }

    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n---------------------\n" + card.getName() + " Editor\n---------------------\n");
        System.out.println(card.toString());
        run();
    }
}
