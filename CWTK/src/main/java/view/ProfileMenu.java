package view;

import controller.LoginController;
import controller.MainController;
import controller.ProfileController;
import model.App;
import model.Captcha;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu implements Menu {
    private String input;
    private boolean exit;
    private ArrayList<Matcher> matchers;
    private ProfileController profileController = new ProfileController();
    private App app = App.getInstance();
    private Scanner s;
    private MainController mainController = new MainController();
    public ProfileMenu() {
        exit = false;
        matchers = new ArrayList<>();
        s = new Scanner(System.in);
        for (int i=0; i<6 ; i++) {
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
        matchers.set(0, App.getCommandMatcher(input, "^\\s*show\\s+information\\s*$"));
        matchers.set(1, App.getCommandMatcher(input, "^\\s*profile\\s+change\\s+-u\\s+(?<username>\\S+)\\s*$"));
        matchers.set(2, App.getCommandMatcher(input, "^\\s*profile\\s+change\\s+-n\\s+(?<nickname>\\S+)\\s*$"));
        matchers.set(3, App.getCommandMatcher(input, "^\\s*profile\\s+change\\s+-e\\s+(?<email>\\S+)\\s*$"));
        matchers.set(4, App.getCommandMatcher(input, "^\\s*profile\\s+change\\s+password\\s+-o\\s+(?<old>\\S+)\\s+-n\\s+(?<new>\\S+)\\s*$"));
        matchers.set(5, App.getCommandMatcher(input, "^\\s*back\\s*$"));
    }

    @Override
    public void check() throws SQLException, IOException, ClassNotFoundException {
        if (matchers.get(0).find()) {
            System.out.println(profileController.showInformation());
        }

        if (matchers.get(1).find()) {
            System.out.println(profileController.changeUsername(matchers.get(1).group("username")));
        }

        if (matchers.get(2).find()) {
            System.out.println(profileController.changeNickname(matchers.get(2).group("nickname")));
        }

        if (matchers.get(3).find()) {
            System.out.println(profileController.changeEmail(matchers.get(2).group("email")));
        }

        if (matchers.get(4).find()) {
            changePassword(matchers.get(4).group("new"), matchers.get(4).group("old"));
        }

        if (matchers.get(5).find()) {
            mainController.back();
        }
    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        if (app.getCurrentUser() == null) {
            System.out.println("There is no user logged into the database. Please use the login or signup menu instead");
            MainMenu mainMenu = new MainMenu();
//            app.setCurrentMenu(mainMenu());
            mainMenu.start();
            return;
        }

        System.out.println("\n----------\nPROFILE MENU\n----------");
        run();
    }

    private void changePassword(String newPass, String oldPass) throws SQLException, IOException, ClassNotFoundException {
        String output;
        output = profileController.changePassword(newPass, oldPass);
        System.out.println(output);

        if (output.equals("Please enter your new password again:")) {
            input = s.nextLine();
            while (!input.equals(newPass)) {
                System.out.println("Please enter your new password again:");
                input = s.nextLine();
            }

            CaptchaMenu captchaMenu = new CaptchaMenu();
            captchaMenu.start();

            System.out.println("Password was changed successfully");
        }
    }
}
