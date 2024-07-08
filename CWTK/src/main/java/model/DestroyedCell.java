package model;

import model.Card.Card;

import java.io.Serial;
import java.io.Serializable;

public class DestroyedCell extends CardPlaceHolder implements Serializable {
    @Serial
    private static final long serialVersionUID = 2889350541420716033L;

    DestroyedCell(Card card) {
        super(card);
    }

    public String toString() {
        return "**\t**";
    }
}
