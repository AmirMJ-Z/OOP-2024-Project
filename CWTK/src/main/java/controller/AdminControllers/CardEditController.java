package controller.AdminControllers;

import model.App;
import model.Card.Card;
import model.Database;
import model.Notebook.Notebook;
import model.Notebook.Page;
import view.AdminMenu.AdminMenu;
import view.AdminMenu.CardEditor;
import view.AdminMenu.DeleteConfirmationPopup;
import view.GameMenu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CardEditController {
    private Notebook notebook;
    private App app = App.getInstance();
    private Database database = Database.getInstance();
    private ArrayList<Card> cards;

    private void initializeNotebook() throws SQLException {
        notebook = new Notebook<Card>(5);
        cards = database.getAllCards();

        for (Card i : cards) {
            notebook.addElement(i);
        }
    }

    public CardEditController() throws SQLException {
        initializeNotebook();
    }
    private String getPageNum() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n----------\n").append("Page ").append(notebook.getCurrentPageNumber()).append("\n----------\n");

        return stringBuilder.toString();
    }

    public void back() throws SQLException, IOException, ClassNotFoundException {
        AdminMenu adminMenu = new AdminMenu();
//        app.setCurrentMenu(adminMenu);
        adminMenu.start();
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

    public String cardEditor(int index) throws SQLException, IOException, ClassNotFoundException {
        if (notebook.getCurrentPage().getElement(index) == null) {
            return "The selected index does not exist in this page";
        }

        Card card = (Card) notebook.getCurrentPage().getElement(index);
        CardEditor cardEditor = new CardEditor(card);
        cardEditor.start();

        return "";
    }
}
