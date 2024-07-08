package controller.ShopControllers;

import model.App;
import model.Card.Card;
import model.Database;
import model.Notebook.Notebook;
import model.Notebook.Page;
import view.GameMenu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CardShopController {
    private Notebook notebook;
    private App app = App.getInstance();
    private Database database = Database.getInstance();
    private ArrayList<Card> cards;

    private void initializeNotebook() throws SQLException {
        notebook = new Notebook<Card>(5);
        cards = database.getAllCards();

        for (Card i : cards) {
            if (app.getCurrentUser().getCardLibrary().getCardLevel1ByName(i.getName()) == null) {
                notebook.addElement(i);
            }
        }
    }

    public CardShopController() throws SQLException {
        initializeNotebook();
    }
    private String getPageNum() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n----------\n").append("Page ").append(notebook.getCurrentPageNumber()).append("\n----------\n").append("\n-------\nBalance\n-------\n").append(app.getCurrentUser().getCoin()).append(" coins\n");

        return stringBuilder.toString();
    }

    public void back() throws SQLException, IOException, ClassNotFoundException {
        GameMenu gameMenu = new GameMenu();
//        app.setCurrentMenu(mainMenu);
        gameMenu.start();
    }

    public String showFirstPage() {
        return notebook.getPage(1).toString() + getPageNum();
    }

    public String nextPage() {
        notebook.next();
        Page page = notebook.getCurrentPage();

        return page.toString() + getPageNum();
    }

    public String previousPage() {
        notebook.previous();
        Page page = notebook.getCurrentPage();

        return page.toString() + getPageNum();
    }

    public String goToPage(int pageNum) {
        return notebook.getPage(pageNum).toString() + getPageNum();
    }

    public String buy(int num) throws SQLException {
        if (notebook.getCurrentPage().getElement(num) == null) {
            return "The selected index does not exist in this page";
        }

        Card card = (Card) notebook.getCurrentPage().getElement(num);

        if (app.getCurrentUser().getCoin() < card.getPrice()) {
            return "You dont have enough coins to buy this card";
        }

        if (app.getCurrentUser().getLevel() < card.getMinLevel()) {
            return "You need to be level " + card.getMinLevel() + " to be able to purchase this card";
        }

        app.getCurrentUser().getCardLibrary().addCard(card);
        app.getCurrentUser().reduceCoins(card.getPrice());
        initializeNotebook();

        return "Card " + card.getName() + " Level " + card.getLevel() + " Was purchased successfully.";
    }

}
