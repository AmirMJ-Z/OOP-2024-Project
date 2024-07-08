package view;

import controller.LoginController;
import controller.MainController;
import controller.SignupController;
import model.App;
import model.Database;
import model.User;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu implements Menu{
    private String input;
    private boolean exit;
    private ArrayList<Matcher> matchers;
    private LoginController loginController = new LoginController();
    private App app = App.getInstance();
    private Scanner s;
    private MainController mainController = new MainController();
    private Database database = Database.getInstance();

    public LoginMenu() {
        exit = false;
        matchers = new ArrayList<>();
        s = new Scanner(System.in);
        for (int i=0; i<4 ; i++) {
            matchers.add(null);
        }
    }
    @Override
    public void run() throws SQLException, IOException, ClassNotFoundException {
        while(!exit) {
            input = s.nextLine();
            setMatchers();
            check();
        }
    }

    @Override
    public void setMatchers() {
        matchers.set(0, App.getCommandMatcher(input, "^\\s*back\\s*$"));
        matchers.set(1, App.getCommandMatcher(input, "^\\s*user\\s+login\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>\\S+)\\s*$"));
        matchers.set(2, App.getCommandMatcher(input, "^\\s*forgot\\s+my\\s+password\\s+-u\\s+(?<username>\\S+)\\s*$"));
        matchers.set(3, App.getCommandMatcher(input, "^\\s*logout\\s*$"));

    }

    @Override
    public void check() throws SQLException, IOException, ClassNotFoundException {
        if (matchers.get(0).find()) {
            mainController.back();
        }

        if (matchers.get(1).find() && app.getCurrentUser() == null) {
            System.out.println(loginController.login(matchers.get(1).group("username"), matchers.get(1).group("password")));
        }

        if (matchers.get(2).find()) {
            PasswordRecoveryMenu passwordRecoveryMenu = new PasswordRecoveryMenu(matchers.get(2).group("username"));
            passwordRecoveryMenu.start();
        }

        if (matchers.get(3).find()) {
            System.out.println(loginController.logout());
        }

    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n----------\nLOGIN MENU\n----------");
        run();
    }
}
