package controller;

import model.App;
import model.Card.Card;
import model.Game;
import view.RoundEnding;

import java.io.IOException;
import java.sql.SQLException;

public class GameLauncherController {
    private Game game = App.getInstance().getCurrentGame();
    int len = game.getTimeLine().getRow1().size();

    public String printTheGame() {
        return game.toString();
    }
    public String selectCard(int player, int num) {
        Card card;

        if (player != 1 && player != 2) {
            return "Please select player 1 or 2";
        }

        if (num < 1 || num > 6) {
            return "Please choose the card number";
        }

        if (player == 1) {
            if (num == 6 && game.getPlayer1Deck().getCards().size() != 6) {
                return "Please choose the card number";
            }

            card = game.getPlayer1Deck().getCards().get(num-1);
        }

        else {
            if (num == 6 && game.getPlayer2Deck().getCards().size() != 6) {
                return "Please choose the card number";
            }

            card = game.getPlayer2Deck().getCards().get(num-1);
        }

        return card.toString();
    }

    public String placeCard(int num, int block) {
        int player;

        if (game.getPlayingPlayer() == game.getUser1()) {
            player = 1;
        }

        else {
            player = 2;
        }

        if (block > len || block < 1) {
            return "Please choose the block number";
        }

        if (selectCard(player, num).equals("Please choose the card number")) {
            return "Please choose the card number";
        }

        Card card = game.getPlayer2Deck().getCards().get(num-1);

        if (!game.getTimeLine().canBePlaced(player, card.getDuration(), block)) {
            return "This card cannot be placed in this position";
        }

        game.getTimeLine().placeCard(card, block, player);
        game.nextRound();

        return "Card was successfully placed in the position";
    }

    public void roundEnd() throws SQLException, IOException, ClassNotFoundException {
        game.resetRemainingRounds();
        RoundEnding roundEnding = new RoundEnding();
        roundEnding.start();
    }

    public void resetTimeLIne() {
        game.getTimeLine().clear();
    }
}
