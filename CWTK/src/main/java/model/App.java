package model;

import view.MainMenu;
import view.Menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private final static App app = new App();
    private Menu currentMenu;
    private App() {
        currentMenu = new MainMenu();
    };
    public static App getInstance() {
        return app;
    }

    public void setCurrentMenu(Menu menu) {
        currentMenu = menu;
    }
    private Game currentGame;

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game game) {
        this.currentGame = game;
    }
    public static Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    public void run() throws SQLException, IOException, ClassNotFoundException {
        currentMenu.start();
    }

    public boolean validUsername(String username) {
        char character;

        for (int i=0; i<username.length(); i++) {
            character = username.charAt(i);

            if (!Character.isDigit(character) && !Character.isAlphabetic(character) && character != '_') {
                return false;
            }
        }

        return true;
    }
}
