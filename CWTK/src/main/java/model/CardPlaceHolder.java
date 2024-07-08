package model;

import model.Card.Card;

import java.io.Serial;
import java.io.Serializable;

public class CardPlaceHolder implements Serializable {
    @Serial
    private static final long serialVersionUID = -8272641444882599906L;
    private int point;
    private int acc;
    private String name;

    CardPlaceHolder(Card card) {
        if (card != null) {
            point = card.getPoint() / card.getDuration();
            acc = card.getACC();
            name = card.getName();
        }

        else {
            point = 0;
            acc = 0;
            name = null;
        }
    }

    public void buff() {
        point *= (int) 1.25;
        acc *= (int) 1.25;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(acc).append("\t").append(point);

        return stringBuilder.toString();
    }
}
