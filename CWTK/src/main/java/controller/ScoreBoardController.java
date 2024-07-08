package controller;

import model.App;
import model.Database;
import model.Game;
import model.GameComparators.GameComparatorDate;
import model.GameComparators.GameComparatorLevel;
import model.GameComparators.GameComparatorResult;
import model.Notebook.Notebook;
import model.Notebook.Page;
import model.User;
import model.UserComparators.CoinComparator;
import model.UserComparators.LevelComparator;
import view.GameMenu;
import view.Main;
import view.MainMenu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScoreBoardController {
    private Notebook notebook;
    private App app = App.getInstance();
    private Database database = Database.getInstance();

    private String getPageNum() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n----------\n").append("Page ").append(notebook.getCurrentPageNumber()).append("\n----------\n");

        return stringBuilder.toString();
    }

    public ScoreBoardController() throws SQLException {
        initializeNotebook();
    }

    private void initializeNotebook() throws SQLException {
        notebook = new Notebook<User>(10);
        ArrayList<User> users = database.getAllPlayers();

        for (User i : users) {
            notebook.addElement(i);
        }
    }

    public void back() throws SQLException, IOException, ClassNotFoundException {
        MainMenu mainMenu = new MainMenu();
//        app.setCurrentMenu(mainMenu);
        mainMenu.start();
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

    public String sortByCoin() {
        CoinComparator coinComparator = new CoinComparator();
        notebook.sortElements(coinComparator);

        return notebook.getCurrentPage().toString();
    }

    public String sortByLevel() {
        LevelComparator levelComparator = new LevelComparator();
        notebook.sortElements(levelComparator);

        return notebook.getCurrentPage().toString();
    }

    public String sortByName() throws SQLException {
        initializeNotebook();

        return notebook.getCurrentPage().toString();
    }
}
