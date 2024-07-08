package model;

import java.io.Serial;
import java.io.Serializable;

public class PasswordRecoveryQuestion implements Serializable {
    @Serial
    private static final long serialVersionUID = -2766207689887209006L;
    private Question question;
    private String answer;

    public PasswordRecoveryQuestion(Question question, String answer) {
        this.question = question;
        this.answer = answer.trim();
    }

    public Question getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean checkAnswer(String answer) {
        return (this.answer.equals(answer.trim()));
    }
}
