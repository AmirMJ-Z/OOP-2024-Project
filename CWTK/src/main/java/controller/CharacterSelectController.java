package controller;

import model.App;
import model.CardType;
import view.GameLauncher;

import java.io.IOException;
import java.sql.SQLException;

public class CharacterSelectController {
    private App app = App.getInstance();

    public String user1Select(int num) {
        app.getCurrentGame().setUser1Character(CardType.getCardTypeByNum(num));
        return "Player 1 selected " + CardType.getCardTypeByNum(num).toString();
    }

    public String user2Select(int num) {
        app.getCurrentGame().setUser2Character(CardType.getCardTypeByNum(num));
        return "Player 2 selected " + CardType.getCardTypeByNum(num).toString();
    }

    public void gameLauncher() throws SQLException, IOException, ClassNotFoundException {
        GameLauncher gameLauncher = new GameLauncher();
        gameLauncher.start();
    }
}
