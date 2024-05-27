package Model;

import java.util.Random;

public class Captcha {
    private int numberOfDigits;
    private int[] digits;
    private String[] lines;

    public Captcha(int[] digits) {
        numberOfDigits = digits.length;
        this.digits = new int[this.numberOfDigits];
        System.arraycopy(digits, 0, this.digits, 0, numberOfDigits);
        lines = new String[6];

        for (int i=0; i<6; i++) {
            StringBuilder line = new StringBuilder();
            for (int j=0; j<numberOfDigits; j++) {
                line.append(asciiNumber.getAsciiByInt(digits[j])[i]);
            }
            lines[i] = line.toString();
        }

        Random random = new Random();
        int randomNum = random.nextInt(5);
        if (randomNum == 4) {
            addParazite("-");
        }

        else if (randomNum == 3) {
            addParazite("~");
        }
    }

    public static Captcha generateCaptcha() {
        Random random = new Random();
        int[] digits = new int[random.nextInt(3)+5];
        for (int i=0; i<digits.length; i++) {
            digits[i] = random.nextInt(9);
        }
        return new Captcha(digits);
    }

    public void addParazite(String p) {
        Random random = new Random();
        int row  = random.nextInt(3)+1;
        int num = 0;
        for (String i : lines) {
            if (i.length() > num) {
                num = i.length();
            }
        }

        StringBuilder s = new StringBuilder();
        for (int i=0; i<num; i++) {
            s.append(p);
        }
        lines[row] = s.toString();
    }

    public String[] getLines() {
        return lines;
    }

    public boolean isValid(String input) {
        if (input.length() != digits.length) {
            return false;
        }
        for (int i=0; i<input.length(); i++) {
            if (!Character.toString(input.charAt(i)).equals(String.valueOf(digits[i]))) {
                return false;
            }
        }

        return true;
    }

}
