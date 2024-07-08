package model.Card;

import model.CardType;
import model.Game;

import java.io.Serial;
import java.io.Serializable;

public class Card implements Serializable {
    @Serial
    private static final long serialVersionUID = 4298218361609102071L;

    protected int duration;
    protected int point;
    protected int ACC;
    protected String name;
    private int price;
    private int level = 1;
    private int minLevel;
    private CardType cardType;

    public int getDuration() {
        return duration;
    }

    public int getPoint() {
        return point;
    }

    public int getACC() {
        return ACC;
    }
    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public Card(String name, int point, int ACC, int duration, int price, int level, int minLevel, CardType cardType) {
        this.minLevel = minLevel;
        this.level = level;
        this.name = name;
        this.point = point;
        this.ACC = ACC;
        this.duration = duration;
        this.price = price;
        this.cardType = cardType;
    }

    @Override
    public Card clone() {
        return new Card(this.name, this.point, this.ACC, this.duration, this.price, this.level, this.minLevel, this.cardType);
    }

    public int getPrice() {
        return price;
    }

    public Card updradeCard() {
        if (level == 1) {
            return new Card(this.name, this.point + this.duration, this.ACC + 5, this.duration, (int) (this.price / 2), this.level + 1, this.minLevel + 1, this.cardType);
        }

        return new Card(this.name, this.point + this.duration, this.ACC + 5, this.duration, (int) (this.price  * 1.25), this.level + 1, this.minLevel + 1, this.cardType);
    }

    public Card getCardByLevel(int level) {
        Card card = this;

        for (int i=0; i<level-1; i++) {
            card = card.updradeCard();
        }

        return card;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setACC(int ACC) {
        this.ACC = ACC;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nName:\t").append(name).append("\n")
                .append("Point:\t").append(point).append("\n")
                .append("ACC:\t").append(ACC).append("\n")
                .append("Duration:\t").append(duration).append("\n")
                .append("Price:\t").append(price).append("\n")
                .append("Minimum level to upgrade:\t").append(minLevel).append("\n");

        return stringBuilder.toString();
    }

    public CardType getCardType() {
        return cardType;
    }
}
