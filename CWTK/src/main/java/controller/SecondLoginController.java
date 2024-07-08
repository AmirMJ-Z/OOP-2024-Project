package controller;

import model.*;
import model.Time.Countdown;
import view.PlayMenu;

import java.io.IOException;
import java.sql.SQLException;

public class SecondLoginController {
    private int failed_attempts = 0;
    private Countdown countdown = null;
    private Database database = Database.getInstance();
    private App app = App.getInstance();
    public String login(String username, String password) throws SQLException, IOException, ClassNotFoundException {
        password = password.trim();

        if (countdown != null && countdown.timeRemaining() != 0) {
            return "Try again after " + countdown.timeRemaining() + " seconds";
        }

        if (database.getUserByUsername(username) == null) {
            return "Username doesn’t exist!";
        }

        if (!database.getUserByUsername(username).getPassword().checkPassword(password)) {
            failed_attempts += 1;
            countdown = new Countdown(failed_attempts * 5);
            countdown.start();
            return "Password and Username don’t match!";
        }

        app.setCurrentGame(new Game(app.getCurrentUser(), database.getUserByUsername(username)));
        failed_attempts = 0;
        if (countdown != null) {
            countdown.reset();
        }
        return "You logged in successfully";
    }

    public void back() throws SQLException, IOException, ClassNotFoundException {
        PlayMenu playMenu = new PlayMenu();
        playMenu.start();
    }
}
