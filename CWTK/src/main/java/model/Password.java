package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Random;

public class Password implements Serializable {
    @Serial
    private static final long serialVersionUID = 6518656575505326191L;
    private String password;

    public String getPassword() {
        return password;
    }

    public Password(String password) {
        this.password = password;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public static boolean isStrongPassword(Password password) {
        if (password.getPassword().length() < 8) {
            return false;
        }

        boolean lower = false, upper = false, other = false;
        char character;

        for (int i=0; i<password.getPassword().length(); i++) {
            character = password.getPassword().charAt(i);

            if (Character.isUpperCase(character) && !upper) {
                upper = true;
            }

            if (Character.isLowerCase(character) && !lower) {
                lower = true;
            }

            if (!Character.isDigit(character) && !Character.isLetter(character) && !other) {
                other = true;
            }

            if (lower && upper && other) {
                return true;
            }
        }

        return false;
    }

    public static Password generatePassword() {
        int length;

        Random random = new Random();
        length = random.nextInt(8, 15);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i=0; i<length; i++) {
            stringBuilder.append(Character.toChars(random.nextInt(33, 126))[0]);
        }

        return new Password(stringBuilder.toString());
    }

    public String toString() {
        return password;
    }
}
