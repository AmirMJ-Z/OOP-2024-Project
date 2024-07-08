package model;

import java.io.Serializable;

public enum CardType implements Serializable {
    Armstrong("Neil Armstrong"),
    Aldrin("Buzz Aldrin"),
    Gagarin("Yuri Gagarin"),
    Glenn("John Glenn");
    private String name;
    CardType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public static CardType getCardTypeByNum(int num) {
        if (num == 1) {
            return Aldrin;
        }

        if (num == 2) {
            return Armstrong;
        }

        if (num == 3) {
            return Gagarin;
        }

        if (num == 4) {
            return Glenn;
        }

        return null;
    }


}
