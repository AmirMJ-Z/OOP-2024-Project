package model.Card.Spell;

import model.Game;
import model.User;

public class DiminishingTouch extends SpellCard{
    private User owner;

    DiminishingTouch(User owner) {
        this.name = "Diminishing Touch";
        this.owner = owner;
        this.duration = 3;
    }
    @Override
    void takeAction(Game game) {

    }

    @Override
    void upgrade() {

    }
}
