package model.Card.Spell;

import model.Game;
import model.User;

public class CurseOfWeakening extends SpellCard{
    private User owner;

    CurseOfWeakening(User owner) {
        this.name = "Curse of Weakening";
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
