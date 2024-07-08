package view;

import controller.LoginController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class PasswordRecoveryMenu implements MiniMenu{
    private LoginController loginController = new LoginController();
    private String username;
    private String input;
    private Scanner s = new Scanner(System.in);

    PasswordRecoveryMenu(String username) {
        this.username = username;
    }
    @Override
    public void run() throws SQLException, IOException, ClassNotFoundException {
        String output;
        output = loginController.passwordRecovery(username);
        System.out.println(output);

        if (output.equals("This username does not exist in the database")) {
            return;
        }

        input = s.nextLine();
        output = loginController.checkRecoveryAnswer(username, input);
        System.out.println(output);

        while (output.equals("Answer to the security question is not valid")) {
            System.out.println(loginController.getQuestion());
            input = s.nextLine();
            output = loginController.checkRecoveryAnswer(username, input);
            System.out.println(output);
        }

        input = s.nextLine();
        output = loginController.setPassword(username, input);
        System.out.println(output);

        while (!output.equals("Password was changed successfully")) {
            input = s.nextLine();
            output = loginController.setPassword(username, input);
            System.out.println(output);
        }
    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        run();
    }
}
