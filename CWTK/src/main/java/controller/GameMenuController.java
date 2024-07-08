package controller;

import model.App;
import model.Card.Card;
import view.GameHistory;
import view.PlayMenu;
import view.Shops.ShopMenu;

import java.io.IOException;
import java.sql.SQLException;

public class GameMenuController {
    private App app = App.getInstance();

    public void shopMenu() throws SQLException, IOException, ClassNotFoundException {
        ShopMenu shopMenu = new ShopMenu();
//        App.getInstance().setCurrentMenu(shopMenu);
        shopMenu.start();
    }

    public void gameHistory() throws SQLException, IOException, ClassNotFoundException {
        GameHistory gameHistory = new GameHistory();
        //        App.getInstance().setCurrentMenu(gameHistory);
        gameHistory.start();
    }

    public String showCards() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Card i : app.getCurrentUser().getCardLibrary().getCards()) {
            stringBuilder.append("\t|\t").append(i.toString()).append("\t|\n");
        }

        return stringBuilder.toString();
    }

    public void playMenu() throws SQLException, IOException, ClassNotFoundException {
        PlayMenu playMenu = new PlayMenu();
        //        App.getInstance().setCurrentMenu(playMenu);
        playMenu.start();
    }
}
