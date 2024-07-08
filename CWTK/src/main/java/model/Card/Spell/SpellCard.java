package model.Card.Spell;

import model.Game;

public abstract class SpellCard {
    protected int duration;
    protected String name;
    abstract void takeAction(Game game);
    abstract void upgrade(); //TODO: Implement Method
}
