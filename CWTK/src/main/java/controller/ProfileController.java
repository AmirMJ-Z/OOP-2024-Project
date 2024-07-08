package controller;

import model.*;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;

public class ProfileController {
    private App app = App.getInstance();
    private Database database = Database.getInstance();
    public String showInformation() {
        StringBuilder stringBuilder = new StringBuilder();
        User user = app.getCurrentUser();

        stringBuilder.append("\n--------------------------\n")
                .append("Username: ").append(user.getUsername()).append("\n")
                .append("Password: ").append(user.getPassword()).append("\n")
                .append("Nickname: ").append(user.getNickname()).append("\n\n")
                .append("\tRecovery Question: ").append(user.getPasswordRecoveryQuestion().getQuestion()).append("\n")
                .append("\tAnswer: ").append(user.getPasswordRecoveryQuestion().getAnswer()).append("\n\n")
                .append("Email: ").append(user.getEmail())
                .append("\n--------------------------\n");

        return stringBuilder.toString();
    }

    public String changeUsername(String username) throws SQLException, IOException, ClassNotFoundException {
        if (database.getUserByUsername(username) != null) {
            if (app.getCurrentUser().getUsername().equals(username)) {
                return "The username is unchanged";
            }

            return "This username already exists in the data base";
        }

        app.getCurrentUser().setUsername(username);
        database.updateUser(app.getCurrentUser());
        return "Username was successfully changed to " + username;
    }

    public String changeNickname(String nickName) throws SQLException {
        if (app.getCurrentUser().getNickname().equals(nickName)) {
            return "The nickname is unchanged";
        }

        app.getCurrentUser().setNickname(nickName);
        database.updateUser(app.getCurrentUser());
        return "Nickname was successfully changed to " + nickName;
    }

    public String changeEmail(String email) throws SQLException {
        if (!Email.isEmail(email)) {
            return "Email format is invalid";
        }

        if (app.getCurrentUser().getEmail().getEmail().equals(email)) {
            return "Email is unchanged";
        }

        app.getCurrentUser().setEmail(new Email(email));
        database.updateUser(app.getCurrentUser());
        return "Email was successfully changed to " + email;
    }

    public String changePassword(String newPass, String oldPass) throws SQLException {
        if (!app.getCurrentUser().getPassword().checkPassword(oldPass)) {
            return "Current password is incorrect";
        }

        Password password = new Password(newPass);

        if (app.getCurrentUser().getPassword().getPassword().equals(newPass)) {
            return "Please change your password";
        }

        if (!Password.isStrongPassword(password)) {
            return "Password should contain an uppercase letter, a lowercase, and a non digit or letter character and should also have at least 8 characters";
        }

        app.getCurrentUser().setPassword(password);
        database.updateUser(app.getCurrentUser());
        return "Please enter your new password again:";
    }
}
