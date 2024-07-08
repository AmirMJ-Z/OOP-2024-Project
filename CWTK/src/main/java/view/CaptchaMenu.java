package view;

import controller.MainController;
import controller.ProfileController;
import model.App;
import model.Captcha;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class CaptchaMenu implements MiniMenu{
    private String input = "";
    private App app = App.getInstance();
    private Scanner s;
    private Captcha captcha;

    CaptchaMenu() {
        s = new Scanner(System.in);
        captcha = Captcha.generateCaptcha();
    }
    @Override
    public void run() throws SQLException, IOException, ClassNotFoundException {

        while(!captcha.isValid(input)) {
            captcha = Captcha.generateCaptcha();
            System.out.println(captcha);
            System.out.println("Please sumbit the captcha to prove you are not a robot.");
            input = s.nextLine();
        }

        System.out.println("Captcha was submitted successfully");
    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n---------------\nI'M NOT A ROBOT\n---------------\n");
        run();
    }
}
