package controller.AdminControllers;

import model.App;
import model.Card.Card;
import model.Database;
import model.Notebook.Notebook;
import model.Notebook.Page;
import model.User;
import view.AdminMenu.AdminMenu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayersController {
    private Notebook notebook;
    private App app = App.getInstance();
    private Database database = Database.getInstance();
    private ArrayList<User> players;

    private void initializeNotebook() throws SQLException {
        notebook = new Notebook<User>(5);
        players = database.getAllPlayers();

        for (User i : players) {
            notebook.addElement(i);
        }
    }

    public PlayersController() throws SQLException {
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

}
