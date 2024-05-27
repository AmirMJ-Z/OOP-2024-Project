package Controller;

import Model.Captcha;

public class RegistrationMenuController {
    public void generateCaptcha() {
        Captcha captcha = Captcha.generateCaptcha();
        for (String i : captcha.getLines()) {
            System.out.println(i);
        }
    }
}
