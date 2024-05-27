package View;

import Controller.RegistrationMenuController;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static RegistrationMenuController registrationMenuController = new RegistrationMenuController();
    public static void main(String[] args) {
        registrationMenuController.generateCaptcha();
    }
}