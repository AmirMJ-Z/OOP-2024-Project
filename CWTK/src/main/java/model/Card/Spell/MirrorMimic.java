package model.Card.Spell;

import model.Game;
import model.User;

public class MirrorMimic extends SpellCard{
    private User owner;

    MirrorMimic(User owner) {
        this.name = "Mirror Mimic";
        this.owner = owner;
        this.duration = 2;
    }

    @Override
    void takeAction(Game game) {

    }

    @Override
    void upgrade() {

    }
}
