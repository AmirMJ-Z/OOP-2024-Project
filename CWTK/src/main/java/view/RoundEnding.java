package view;

import controller.GameLauncherController;
import controller.MainController;
import model.App;
import model.Database;
import model.Game;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class RoundEnding implements MiniMenu{
    private GameLauncherController gameLauncherController = new GameLauncherController();
    private App app = App.getInstance();
    private Scanner s;
    private Database database = Database.getInstance();
    private Game game = app.getCurrentGame();

    private int point1 = 0;
    private int point2 = 0;

    public RoundEnding() {
        s = new Scanner(System.in);
    }
    @Override
    public void run() throws SQLException, IOException, ClassNotFoundException {
        point1 += game.getPlayer1Points();
        point2 += game.getPlayer2Points();

        for (int i=0; i<game.getTimeLine().getRow1().size(); i++) {
            game.getTimeLine().processCell(i+1);

            System.out.println(game);

            game.processGameEnd();

            if (game.isGameEnd()) {
                if (game.getPlayer2HP() <= 0) {
                    game.setWinner(game.getUser1());
                    if (game.getUser1().increaseXP(point1)) {
                        System.out.println("Player " + game.getUser1().getUsername() + " was leveled up to the level " + game.getUser1().getLevel());
                        System.out.println("You got " + game.getUser1().getLevel() * 7 + " coins");
                        game.getUser1().addCoin(game.getUser1().getLevel() * 7);
                    }
                }

                else {
                    game.setWinner(game.getUser2());
                    if (game.getUser2().increaseXP(point1)) {
                        System.out.println("Player " + game.getUser2().getUsername() + " was leveled up to the level " + game.getUser2().getLevel());
                        System.out.println("You got " + game.getUser2().getLevel() * 7 + " coins");
                        game.getUser2().addCoin(game.getUser2().getLevel() * 7);
                    }
                }

                database.addGame(game);
                return;
            }
        }

        System.out.println("\n---------------------------\nEND\n---------------------------\n");
    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n---------\nROUND END\n---------\n");
        run();
    }
}
