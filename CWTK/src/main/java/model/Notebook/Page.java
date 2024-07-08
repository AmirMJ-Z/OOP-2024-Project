package model.Notebook;

import java.util.ArrayList;

import model.App;
import model.Card.Card;
import model.Game;
import model.User;

public class Page<T> {
    private final int MAXIMUM_CAPACITY;

    Page(int MAXIMUM_CAPACITY) {
        this.MAXIMUM_CAPACITY = MAXIMUM_CAPACITY;
    }
    private ArrayList<T> elements = new ArrayList<>();
    public void addElement(T element) {
        if (elements.size() == MAXIMUM_CAPACITY) {
            return;
        }

        elements.add(element);
    }

    public int getCapacity() {
        return MAXIMUM_CAPACITY - elements.size();
    }

    public T getElement(int num) {
        if (num > elements.size() || num <= 0) {
            return null;
        }

        return elements.get(num-1);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (elements.getFirst().getClass() == Game.class) {
            String result;
            User user;

            for (T i : elements) {
                Game game = (Game) i;
                if (game.getWinner().getUsername().equals(App.getInstance().getCurrentUser().getUsername())) {
                    result = "WIN";
                }

                else {
                    result = "LOSE";
                }

                if (game.getUser1().getUsername().equals(App.getInstance().getCurrentUser().getUsername())) {
                    user = game.getUser2();
                }

                else {
                    user = game.getUser1();
                }

                stringBuilder.append(game.getTime().getYear()).append("/").append(game.getTime().getDay()).append("/").append(game.getTime().getMonth()).append(" - ")
                        .append(game.getTime().getHour()).append(":").append(game.getTime().getMinute()).append(":").append(game.getTime().getSecond())
                        .append("\t").append(result).append("\t").append(user.getUsername()).append(": ").append(user.getLevel()).append("\n");

            }
            return stringBuilder.toString();
        }

        if (elements.getFirst().getClass() == Card.class) {
            for (int i=0; i< elements.size(); i++) {
                Card card = (Card) elements.get(i);

                stringBuilder.append(i+1).append("-\t").append(card.getName()).append("\t").append("Points:\t").append(card.getPoint()).append("\tACC:\t").append(card.getACC()).append("\tDuration:\t").append(card.getDuration()).append("\tPrice:\t").append(card.getPrice()).append("\n");

            }
            return stringBuilder.toString();
        }

        return null;
    }

}
