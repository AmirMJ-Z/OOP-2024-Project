package View;

import Controller.RegistrationMenuController;
import Model.Captcha;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Matcher getCommandMatcher(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
    private static RegistrationMenuController registrationMenuController = new RegistrationMenuController();
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = "";

        while (!getCommandMatcher("\\s*end\\s*", input).find()) {
            Captcha captcha = registrationMenuController.generateCaptcha();
            input = s.nextLine();
            registrationMenuController.captchaValidation(input, captcha);
        }

    }
}