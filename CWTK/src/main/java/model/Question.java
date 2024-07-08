package model;

import java.io.Serializable;

public enum Question implements Serializable {
    A("What is your father’s name?"),
    B("What is your favourite color?"),
    C("What was the name of your first pet?");

    private String question;

    public String getQuestion() {
        return question;
    }
    Question(String question) {
        this.question = question;
    }

    public static Question getQuestionByNum(int n) {
        if (n == 1) {
            return A;
        }

        if (n == 2) {
            return B;
        }

        if (n == 3) {
            return C;
        }

        return null;
    }

    public String toString() {
        return question;
    }
}
