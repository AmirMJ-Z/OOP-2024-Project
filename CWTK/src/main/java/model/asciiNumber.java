package model;

public enum asciiNumber {;
    private static String[] getArrayByString(String s) {
        return s.split("\n");
    }
    static final String[] dig_0 = getArrayByString(" a8888a  \n" +
            "d8' ..8b \n" +
            "88 .P 88 \n" +
            "88 d' 88 \n" +
            "Y8'' .8P \n" +
            " Y8888P  ");
    static final String[] dig_1 = getArrayByString("d88  \n" +
            " 88  \n" +
            " 88  \n" +
            " 88  \n" +
            " 88  \n" +
            "d88P ");
    static final String[] dig_2 = getArrayByString("d8888b. \n" +
            "    `88 \n" +
            ".aaadP' \n" +
            "88'     \n" +
            "88.     \n" +
            "Y88888P ");
    static final String[] dig_3 = getArrayByString("d8888b. \n" +
            "    `88 \n" +
            " aaad8' \n" +
            "    `88 \n" +
            "    .88 \n" +
            "d88888P ");
    static final String[] dig_4 = getArrayByString("dP   dP \n" +
            "88   88 \n" +
            "88aaa88 \n" +
            "     88 \n" +
            "     88 \n" +
            "     dP ");
    static final String[] dig_5 = getArrayByString("888888P \n" +
            "88'     \n" +
            "88baaa. \n" +
            "    `88 \n" +
            "     88 \n" +
            "d88888P ");
    static final String[] dig_6 = getArrayByString(".d8888P \n" +
            "88'     \n" +
            "88baaa. \n" +
            "88` `88 \n" +
            "8b. .d8 \n" +
            "`Y888P' ");
    static final String[] dig_7 = getArrayByString("d88888P \n" +
            "    d8' \n" +
            "   d8'  \n" +
            "  d8'   \n" +
            " d8'    \n" +
            "d8'     ");
    static final String[] dig_8 = getArrayByString(".d888b. \n" +
            "Y8' `8P \n" +
            "d8bad8b \n" +
            "88` `88 \n" +
            "8b. .88 \n" +
            "Y88888P ");

    static final String[] dig_9 = getArrayByString(".d888b. \n" +
            "Y8' `88 \n" +
            "`8bad88 \n" +
            "    `88 \n" +
            "d.  .88 \n" +
            "`8888P  ");

    public static String[] getAsciiByInt(int i) {
        if (i == 0) {
            return asciiNumber.dig_0;
        }

        if (i == 1) {
            return asciiNumber.dig_1;
        }

        if (i == 2) {
            return asciiNumber.dig_2;
        }

        if (i == 3) {
            return asciiNumber.dig_3;
        }

        if (i == 4) {
            return asciiNumber.dig_4;
        }

        if (i == 5) {
            return asciiNumber.dig_5;
        }

        if (i == 6) {
            return asciiNumber.dig_6;
        }

        if (i == 7) {
            return asciiNumber.dig_7;
        }

        if (i == 8) {
            return asciiNumber.dig_8;
        }

        if (i == 9) {
            return asciiNumber.dig_9;
        }

        return null;
    }


}
