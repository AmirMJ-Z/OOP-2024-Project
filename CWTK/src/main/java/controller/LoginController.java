package controller;

import model.*;
import model.Time.Countdown;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    private int failed_attempts = 0;
    private Countdown countdown = null;
    private Database database = Database.getInstance();
    private App app = App.getInstance();
    private Question question;
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

        app.setCurrentUser(database.getUserByUsername(username));
        failed_attempts = 0;
        if (countdown != null) {
            countdown.reset();
        }
        return "You logged in successfully";
    }

    public String logout() throws SQLException {
        User user = app.getCurrentUser();
        database.updateUser(app.getCurrentUser());
        app.setCurrentUser(null);

        return "User " + user.getUsername() + " logged out successfully";
    }

    public String passwordRecovery(String username) throws SQLException, IOException, ClassNotFoundException {
        if (database.getUserByUsername(username) == null) {
            return "This username does not exist in the database";
        }

        question = database.getUserByUsername(username).getPasswordRecoveryQuestion().getQuestion();
        return database.getUserByUsername(username).getPasswordRecoveryQuestion().getQuestion().getQuestion();
    }

    public String checkRecoveryAnswer(String username, String answer) throws SQLException, IOException, ClassNotFoundException {
        answer = answer.trim();
        if (database.getUserByUsername(username).getPasswordRecoveryQuestion().checkAnswer(answer)) {
            return "New Password:";
        }

        return "Answer to the security question is not valid";
    }

    public String getQuestion() {
        return question.getQuestion();
    }

    public String setPassword(String username, String password) throws SQLException, IOException, ClassNotFoundException {
        password = password.trim();
        Password password1 = new Password(password);

        if (!Password.isStrongPassword(password1)) {
            return "Password should contain an uppercase letter, a lowercase, and a non digit or letter character and should also have at least 8 characters\nNew password:";
        }

        User user = database.getUserByUsername(username);

        user.setPassword(password1);
        database.updateUser(user);
        return "Password was changed successfully";
    }
}
