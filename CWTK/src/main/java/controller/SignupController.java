package controller;

import model.*;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class SignupController {
    private Database database = Database.getInstance();
    private App app = App.getInstance();
    public String signup(String username, String password, String passwordconfirmation, String email, String nickname) throws SQLException, IOException, ClassNotFoundException {
        username = username.trim();
        password = password.trim();
        passwordconfirmation = passwordconfirmation.trim();
        email = email.trim();
        nickname = nickname.trim();

        if (username.isEmpty() || password.isEmpty() || passwordconfirmation.isEmpty() || email.isEmpty() || nickname.isEmpty()) {
            return "All the field have to be filled in.";
        }

        if (!app.validUsername(username)) {
            return "Username should only contain letters (Both uppercase abd lowercase), digits and underscore";
        }

        if (database.getUserByUsername(username) != null) {
            return "Username already exists in the database. Please choose another one.";
        }

        if (!password.equals(passwordconfirmation)) {
            return "Please confirm your password";
        }

        Password password1 = new Password(password);

        if (!password.equals("random")) {
            if (!Password.isStrongPassword(password1)) {
                return "Password should contain an uppercase letter, a lowercase, and a non digit or letter character and should also have at least 8 characters";
            }
        }

        if (!Email.isEmail(email)) {
            return "Invalid format for email";
        }

        User user = new User(username, password1, nickname, new Email(email));
        database.addUser(user);

        return "User added to the database successfully";
    }

    public void securityQuestion(String username, int question, String answer) throws SQLException, IOException, ClassNotFoundException {
        User user = database.getUserByUsername(username);
        PasswordRecoveryQuestion passwordRecoveryQuestion = new PasswordRecoveryQuestion(Question.getQuestionByNum(question), answer);

        user.setSecurityQuestion(passwordRecoveryQuestion);
        database.updateUser(user);
    }

    public String setRandomPassword(String username, Password password) throws SQLException, IOException, ClassNotFoundException {
        User user = database.getUserByUsername(username);
        user.setPassword(password);
        database.updateUser(user);

        return "Password was set successfully";
    }
}
