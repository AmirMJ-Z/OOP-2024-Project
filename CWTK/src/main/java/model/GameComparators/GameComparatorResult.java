package model.GameComparators;

import model.App;
import model.Game;

import java.util.Comparator;

public class GameComparatorResult implements Comparator<Game> {
    private App app = App.getInstance();

    @Override
    public int compare(Game o1, Game o2) {
        if (o1.wonTheGame(app.getCurrentUser()) && !o2.wonTheGame(app.getCurrentUser())) {
            return 1;
        }

        if (!o1.wonTheGame(app.getCurrentUser()) && o2.wonTheGame(app.getCurrentUser())) {
            return -1;
        }

        return 0;
    }
}
