package model.Card.Spell;

import model.Game;
import model.User;

public class DevastationWave extends SpellCard{
    private User owner;

    DevastationWave(User owner) {
        this.name = "Devastation Wave";
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
