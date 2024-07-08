package view;

import controller.MainController;
import controller.SignupController;
import model.App;
import model.Captcha;
import model.Password;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.regex.Matcher;

public class SignupMenu implements Menu{
    private String input;
    private boolean exit;
    private ArrayList<Matcher> matchers;
    private SignupController signupController = new SignupController();
    private App app = App.getInstance();
    private Scanner s;
    private MainController mainController = new MainController();

    public SignupMenu() {
        exit = false;
        matchers = new ArrayList<>();
        s = new Scanner(System.in);
        for (int i=0; i<3 ; i++) {
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
        matchers.set(0, App.getCommandMatcher(input, "^\\s*user\\s+create\\s+-u\\s+(?<username>.+)\\s+-p\\s+(?<password>\\S+)\\s+(?<passwordconfirmation>\\S+)\\s+-email\\s+(?<email>\\S+)\\s+-n\\s+(?<nickname>.+)\\s*$"));
        matchers.set(1, App.getCommandMatcher(input, "^\\s*user\\s+create\\s+-u\\s+(?<username>.+)\\s+-p\\s+random\\s+-email\\s+(?<email>\\S+)\\s+-n\\s+(?<nickname>.+)\\s*$"));
        matchers.set(2, App.getCommandMatcher(input, "^\\s*back\\s*$"));
    }

    @Override
    public void check() throws SQLException, IOException, ClassNotFoundException {
        if (matchers.get(0).find()) {
            Matcher matcher = matchers.get(0);
            String output;
            output = signupController.signup(matcher.group("username"), matcher.group("password"), matcher.group("passwordconfirmation"), matcher.group("email"), matcher.group("nickname"));
            System.out.println(output);

            if (output.equals("User added to the database successfully")) {
                setSecurityQuestion(matcher.group("username"));
                CaptchaMenu captchaMenu = new CaptchaMenu();
                captchaMenu.start();
            }
        }

        if (matchers.get(1).find()) {
            Matcher matcher = matchers.get(1);
            String output;
            output = signupController.signup(matcher.group("username"), "random", "random", matcher.group("email"), matcher.group("nickname"));
            System.out.println(output);

            if (output.equals("User added to the database successfully")) {
                setRandomPassword(matcher.group("username"));

                setSecurityQuestion(matcher.group("username"));
                CaptchaMenu captchaMenu = new CaptchaMenu();
                captchaMenu.start();
            }

        }

        if (matchers.get(2).find()) {
            exit = true;
            mainController.back();
        }

    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n-----------\nSIGNUP MENU\n-----------");
        run();
    }

    public void setRandomPassword(String username) throws SQLException, IOException, ClassNotFoundException {
        String input = "";
        Password password = Password.generatePassword();
        System.out.println("\nYour generated password is: " + password.getPassword() + "\n");
        Scanner s = new Scanner(System.in);

        while(!input.equals(password.getPassword())) {
            System.out.println("Please Confirm your randomly generated password");
            input = s.nextLine();
        }

        System.out.println(signupController.setRandomPassword(username, password));
    }

    public void setSecurityQuestion(String username) throws SQLException, IOException, ClassNotFoundException {
        String regex = "^\\s*question\\s+pick\\s+-q\\s+(?<questionnumber>\\d)\\s+-a\\s+(?<answer>.+)\\s+-c\\s+(?<answerconfirm>.+)\\s*$";
        Matcher matcher;

        while (true) {
            matcher = App.getCommandMatcher(input, regex);

            if (!matcher.find()) {
                System.out.println("\nPlease choose a security question:\n" +
                        "\t1- What is your father’s name?\n" +
                        "\t2- What is your favourite color?\n" +
                        "\t3- What was the name of your first pet?\n");
                input = s.nextLine();
            }

            else if (!matcher.group("answer").equals(matcher.group("answerconfirm"))) {
                System.out.println("Pleases confirm your answer to the security question");
                input = s.nextLine();
            }

            else {
                signupController.securityQuestion(username, Integer.parseInt(matcher.group("questionnumber")), matcher.group("answer"));
                System.out.println("Security question was set successfully");
                return;
            }
        }
    }
}
