package model.Card;

import model.Database;

import java.io.Serial;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class CardLibrary implements Serializable {
    @Serial
    private static final long serialVersionUID = 2909970953899896614L;
    private ArrayList<Card> cards = new ArrayList<>();
    private boolean starterPackReceived = false;
    
    public void starterPack() throws SQLException {
        if (!starterPackReceived) {
            Random random = new Random();
            int index;
            ArrayList<Card> allCards = Database.getInstance().getAllCards();

            for (int i=0; i<15; i++) {
                index = random.nextInt(allCards.size());

                while (getCardLevel1ByName(allCards.get(index).getName()) != null) {
                    index = random.nextInt(allCards.size());
                }

                addCard(allCards.get(index));
            }
            starterPackReceived = true;
        }
    }
    
    public void addCard(Card card) {
        Card newCard = card.clone();
        cards.add(newCard);
    }
    
    public boolean hasReceivedStarterPack() {
        return starterPackReceived;
    }

    public Card getCardLevel1ByName(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name)) {
                return card;
            }
        }

        return null;
    }

    public void upgradeCard(Card card) {
        Card card_ = null;

        for (Card i : cards) {
            if (i.getName().equals(card.getName())) {
                card_ = i;
                break;
            }
        }

        cards.remove(card_);
        card_ = card_.updradeCard();
        cards.add(card_);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
