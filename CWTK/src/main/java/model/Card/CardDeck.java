package model.Card;

import model.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class CardDeck implements Serializable {
    @Serial
    private static final long serialVersionUID = -4617674765414723301L;
    private ArrayList<Card> cards;
    private final int len = 5;
    private User user;

    public CardDeck(User user) {
        this.user = user;
        Random random = new Random();
        int index;

        cards = new ArrayList<>();

        for (int i=0; i<len; i++) {
            index = random.nextInt(user.getCardLibrary().getCards().size());
            cards.add(user.getCardLibrary().getCards().get(index).clone());
        }
    }

    public void addCard() {
        Random random = new Random();
        int index;

        for (int i=0; i<len; i++) {
            if (cards.get(i) == null) {
                index = random.nextInt(user.getCardLibrary().getCards().size());
                cards.set(i, user.getCardLibrary().getCards().get(index).clone());
                return;
            }
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }


}
