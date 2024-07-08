package controller;

import model.App;
import view.CharacterSelectMenu;
import view.SecondLoginMenu;

import java.io.IOException;
import java.sql.SQLException;

public class PlayMenuController {
    private App app = App.getInstance();

    public void casual() throws SQLException, IOException, ClassNotFoundException {
        SecondLoginMenu secondLoginMenu = new SecondLoginMenu();
        secondLoginMenu.start();

        if (app.getCurrentGame().getUser2() != null) {
            CharacterSelectMenu characterSelectMenu = new CharacterSelectMenu();
            characterSelectMenu.start();
        }
    }
}
