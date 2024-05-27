package Controller;

import Model.Captcha;

public class RegistrationMenuController {
    public Captcha generateCaptcha() {
        Captcha captcha = Captcha.generateCaptcha();
        for (String i : captcha.getLines()) {
            System.out.println(i);
        }

        return captcha;
    }

    public void captchaValidation(String input, Captcha captcha) {
        if (captcha.isValid(input)) {
            System.out.println("Captcha is valid");
        }

        else {
            System.out.println("Captcha is not valid");
        }
    }
}
