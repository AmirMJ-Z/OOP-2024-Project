package view.AdminMenu;

import controller.AdminControllers.CardDeleteController;
import model.App;
import view.Popup;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class EditConfirmationPopup implements Popup {
    private String input;
    private Scanner s;

    public EditConfirmationPopup() {
        s = new Scanner(System.in);
    }
    @Override
    public boolean run() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Are you sure you want to dave the changes?");
        input = s.nextLine();

        if (App.getCommandMatcher(input, "^\\s*y\\s*$").find()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean start() throws SQLException, IOException, ClassNotFoundException {
        return run();
    }
}
