package model;

import controller.MainController;
import model.Card.CardDeck;
import model.Card.CardLibrary;

import java.io.Serial;
import java.io.Serializable;
import java.sql.SQLException;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 5582137972130647926L;
    private String username;
    private Password password;
    private String nickname;
    private Email email;
    private PasswordRecoveryQuestion passwordRecoveryQuestion;
    private int level;
    private int xp;
    private int coin;
    private int maxHP;
    private CardLibrary cardLibrary;
    private final int BASE_XP = 300;

    public User(String username, Password password, String nickname, Email email) throws SQLException {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.level = 1;
        this.maxHP = 100;
        this.xp = 0;
        this.coin = 0;
        cardLibrary = new CardLibrary();
    }

    public void addCoin(int amount) {
        coin += amount;
    }

    public boolean increaseXP(int amount) {
        xp += amount;
        return processXP();
    }

    private boolean processXP() {
        if (xp >= BASE_XP * Math.pow(1.25, level)) {
            xp -= BASE_XP * Math.pow(1.25, level);
            levelUp();
            return true;
        }

        return false;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public Email getEmail() {
        return email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public PasswordRecoveryQuestion getPasswordRecoveryQuestion() {
        return passwordRecoveryQuestion;
    }

    public Password getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setSecurityQuestion(PasswordRecoveryQuestion passwordRecoveryQuestion) {
        this.passwordRecoveryQuestion = passwordRecoveryQuestion;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    private void levelUp() {
        level += 1;
    }


    public int getLevel() {
        return level;
    }

    public boolean hasReceivedStarterPack() {
        return cardLibrary.hasReceivedStarterPack();
    }

    public CardLibrary getCardLibrary() {
        return cardLibrary;
    }

    public int getCoin() {
        return coin;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void reduceCoins(int amount) {
        coin -= amount;
    }
}
