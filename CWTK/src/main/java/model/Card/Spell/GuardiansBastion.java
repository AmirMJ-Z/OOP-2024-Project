package model.Card.Spell;

import model.Game;
import model.User;

public class GuardiansBastion extends SpellCard{
    private User owner;

    GuardiansBastion(User owner) {
        this.name = "Guardian's Bastion";
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
