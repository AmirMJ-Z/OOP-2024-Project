package controller.AdminControllers;

import model.App;
import model.Card.Card;
import model.CardType;
import model.Database;
import view.AdminMenu.CardDeleteMenu;
import view.AdminMenu.CardEditMenu;
import view.AdminMenu.PlayersMenu;

import java.io.IOException;
import java.sql.SQLException;

public class AdminController {
    private Database database = Database.getInstance();
    private App app = App.getInstance();
    public String addCard(String name, int point, int acc, int duration, int price, int i, int minlevel) throws SQLException {
        if (database.getCardByName(name) != null) {
            return "There is already a card existing in the database with the same name";
        }

        if (point < 10 || point > 100) {
            return "Point value should be between 10 and 100";
        }

        if (duration < 1 || duration > 5) {
            return "Duration value should be between 1 and 5";
        }

        if (acc < 10 || acc > 50) {
            return "Accuracy value should be between 10 and 50";
        }

        database.addCard(new Card(name, point, acc, duration, price, 1, minlevel, CardType.Armstrong));
        return "Card with the name " + name + " was added to the database successfully";
    }

    public void cardEditMenu() throws SQLException, IOException, ClassNotFoundException {
        CardEditMenu cardEditMenu = new CardEditMenu();
//        app.setCurrentMenu(cardEditMenu);
        cardEditMenu.start();
    }

    public void cardDeleteMenu() throws SQLException, IOException, ClassNotFoundException {
        CardDeleteMenu cardDeleteMenu = new CardDeleteMenu();
//        app.setCurrentMenu(cardDeleteMenu);
        cardDeleteMenu.start();
    }

    public void playersMenu() throws SQLException, IOException, ClassNotFoundException {
        PlayersMenu playersMenu = new PlayersMenu();
//        app.setCurrentMenu(playersMenu);
        playersMenu.start();
    }
}
