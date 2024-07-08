package model;

import model.Card.Card;
import model.Card.CardDeck;
import model.Time.Time;

import java.io.Serial;
import java.io.Serializable;
import java.util.Random;

public class Game implements Serializable {
    @Serial
    private static final long serialVersionUID = -8972668720969359991L;
    private User user1;
    private User user2;

    private boolean gameEnd = false;

    public void processGameEnd() {
        if (player1HP <= 0 || player2HP <= 0) {
            gameEnd = true;
        }
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    public void endGame() {
        gameEnd = true;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public User getPlayingPlayer() {
        return playingPlayer;
    }

    public void setPlayingPlayer(User playingPlayer) {
        this.playingPlayer = playingPlayer;
    }

    public User getOtherPlayer() {
        return otherPlayer;
    }

    public void setOtherPlayer(User otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public int getRemainingRounds() {
        return remainingRounds;
    }

    public void setRemainingRounds(int remainingRounds) {
        this.remainingRounds = remainingRounds;
    }

    public int getPlayer1HP() {
        return player1HP;
    }

    public void setPlayer1HP(int player1HP) {
        this.player1HP = player1HP;
    }

    public int getPlayer2HP() {
        return player2HP;
    }

    public void setPlayer2HP(int player2HP) {
        this.player2HP = player2HP;
    }

    public int getPlayer1Points() {
        return player1Points;
    }

    public void setPlayer1Points(int player1Points) {
        this.player1Points = player1Points;
    }

    public int getPlayer2Points() {
        return player2Points;
    }

    public void setPlayer2Points(int player2Points) {
        this.player2Points = player2Points;
    }

    private User winner;
    private TimeLine timeLine = new TimeLine(this);
    private User playingPlayer;
    private User otherPlayer;
    private Time time;
    private int remainingRounds = 8;
    private int player1HP;
    private int player2HP;
    private int player1Points = 0;
    private int player2Points = 0;
    private CardType user1Character;
    private CardType user2Character;
    private CardDeck player1Deck;
    private CardDeck player2Deck;

    public CardType getUser1Character() {
        return user1Character;
    }

    public void setUser1Character(CardType user1Character) {
        this.user1Character = user1Character;
    }

    public CardType getUser2Character() {
        return user2Character;
    }

    public void setUser2Character(CardType user2Character) {
        this.user2Character = user2Character;
    }

    public TimeLine getTimeLine() {
        return timeLine;
    }

    public Time getTime() {
        return time;
    }

    public CardDeck getPlayer1Deck() {
        return player1Deck;
    }

    public CardDeck getPlayer2Deck() {
        return player2Deck;
    }

    public Game(User user1, User user2) {
        time = new Time();

        Random random = new Random();
        this.user1 = user1;
        this.user2 = user2;

        player1HP = user1.getMaxHP();
        player2HP = user2.getMaxHP();

        if (random.nextInt(4) == 1) {
            playingPlayer = user2;
            otherPlayer = user1;
        }

        else {
            playingPlayer = user1;
            otherPlayer = user2;
        }

        player1Deck = new CardDeck(user1);
        player2Deck = new CardDeck(user2);
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    public boolean wonTheGame(User user) {
        if (user == winner) {
            return true;
        }

        return false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(user2.getUsername()).append("\t").append(user2Character).append("\tPoints: ").append(player2Points).append("\tHP: ").append(player2HP).append("\n");
        for (int i=0; i<player2Deck.getCards().size(); i++) {
            stringBuilder.append(i+1).append("- ").append(player2Deck.getCards().get(i).getName()).append("\t");
        }
        stringBuilder.append("\n");

        stringBuilder.append(timeLine).append("\n");
        for (int i=0; i<player1Deck.getCards().size(); i++) {
            stringBuilder.append(i+1).append("- ").append(player1Deck.getCards().get(i).getName()).append("\t");
        }
        stringBuilder.append("\n");

        stringBuilder.append(user1.getUsername()).append("\t").append(user1Character).append("\tPoints: ").append(player1Points).append("\tHP: ").append(player1HP).append("\n");
        stringBuilder.append("\n").append("-------------------------\n");

        int rem;

        if (remainingRounds % 2 == 0) {
            rem = remainingRounds / 2;
        }

        else {
            rem = ((int) remainingRounds/2) + 1;
        }

        stringBuilder.append("Turn: ").append(playingPlayer.getUsername()).append("\n");
        stringBuilder.append("Remaining rounds: ").append(rem).append("\n");

        stringBuilder.append("-------------------------\n");


        return stringBuilder.toString();
    }

    public void nextRound() {
        remainingRounds -= 1;
        switchPlayers();
    }

    private void switchPlayers() {
        User temp = playingPlayer;
        playingPlayer = otherPlayer;
        otherPlayer = temp;
    }

    public void resetRemainingRounds() {
        remainingRounds = 8;
    }
}
