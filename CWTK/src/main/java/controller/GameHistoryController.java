package controller;

import model.App;
import model.Database;
import model.Game;
import model.GameComparators.GameComparatorDate;
import model.GameComparators.GameComparatorLevel;
import model.GameComparators.GameComparatorResult;
import model.Notebook.Notebook;
import model.Notebook.Page;
import view.GameHistory;
import view.GameMenu;
import view.MainMenu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameHistoryController {
    private Notebook notebook;
    private App app = App.getInstance();
    private Database database = Database.getInstance();

    private String getPageNum() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n----------\n").append("Page ").append(notebook.getCurrentPageNumber()).append("\n----------\n");

        return stringBuilder.toString();
    }

    public GameHistoryController() throws SQLException {
        initializeNotebook();
    }

    private void initializeNotebook() throws SQLException {
        notebook = new Notebook<Game>(10);
        ArrayList<Game> games = database.getGameByUsername(app.getCurrentUser().getUsername());

        for (Game i : games) {
            notebook.addElement(i);
        }
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

    public String sortByDate() {
        GameComparatorDate gameComparatorDate = new GameComparatorDate();
        notebook.sortElements(gameComparatorDate);

        return notebook.getCurrentPage().toString();
    }

    public String sortByLevel() {
        GameComparatorLevel gameComparatorLevel = new GameComparatorLevel();
        notebook.sortElements(gameComparatorLevel);

        return notebook.getCurrentPage().toString();
    }

    public String sortByResult() {
        GameComparatorResult gameComparatorResult = new GameComparatorResult();
        notebook.sortElements(gameComparatorResult);

        return notebook.getCurrentPage().toString();
    }
}
