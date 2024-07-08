package controller.AdminControllers;

import model.Card.Card;
import model.Database;
import view.AdminMenu.CardEditMenu;
import view.AdminMenu.EditConfirmationPopup;

import java.io.IOException;
import java.sql.SQLException;

public class CardEditorController {
    private Card card;
    private Database database = Database.getInstance();

    public CardEditorController(Card card) {
        this.card = card;
    }

    public void save() throws SQLException, IOException, ClassNotFoundException {
        EditConfirmationPopup editConfirmationPopup = new EditConfirmationPopup();
        if (editConfirmationPopup
                .start()) {
            database.updateCard(card);
        }
        CardEditMenu cardEditMenu = new CardEditMenu();
        cardEditMenu.start();
    }

    public String changeName(String name) throws SQLException {
        if (database.getCardByName(name) != null) {
            return "A card with this name already exists inside the database";
        }

        card.setName(name);
        return "Card name was changed to " + name + "successfully";
    }

    public String changeScore(int point) {
        if (point < 10 || point > 100) {
            return "Point value should be between 10 and 100";
        }

        card.setPoint(point);
        return "Card point was changed to " + point + "successfully";
    }

    public String changeACC(int acc) {
        if (acc < 10 || acc > 50) {
            return "Accuracy value should be between 10 and 50";
        }

        card.setACC(acc);
        return "Card accuracy was changed to " + acc + "successfully";
    }

    public String changeDuration(int duration) {
        if (duration < 1 || duration > 5) {
            return "Duration value should be between 10 and 50";
        }

        card.setACC(duration);
        return "Card duration was changed to " + duration + "successfully";
    }

    public String changePrice(int price) {
        card.setACC(price);
        return "Card duration was changed to " + price + "successfully";
    }

    public String changeMinLevel(int minlevel) {
        if (minlevel == 0) {
            return "Level value cannot be zero";
        }

        card.setACC(minlevel);
        return "Card minimum upgrade level was changed to " + minlevel + "successfully";
    }
}
