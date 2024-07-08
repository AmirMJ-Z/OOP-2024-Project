package view;

import controller.GameLauncherController;
import controller.LoginController;
import controller.MainController;
import model.App;
import model.Database;
import model.Game;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameLauncher implements Menu {
    private String input;
    private boolean exit;
    private ArrayList<Matcher> matchers;
    private GameLauncherController gameLauncherController = new GameLauncherController();
    private App app = App.getInstance();
    private Scanner s;
    private MainController mainController = new MainController();
    private Database database = Database.getInstance();
    private Game game = app.getCurrentGame();

    public GameLauncher() {
        exit = false;
        matchers = new ArrayList<>();
        s = new Scanner(System.in);
        for (int i=0; i<4 ; i++) {
            matchers.add(null);
        }
    }
    @Override
    public void run() throws SQLException, IOException, ClassNotFoundException {
        while(!exit) {
            input = s.nextLine();
            setMatchers();
            check();

            if (game.getRemainingRounds() == 0) {
                gameLauncherController.roundEnd();

                if (!game.isGameEnd()) {
                    System.out.println("\n----------\nGAME START\n----------\n");
                    gameLauncherController.resetTimeLIne();
                    System.out.println(gameLauncherController.printTheGame());
                }

                else {
                    exit = true;
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.start();
                }
            }
        }
    }

    @Override
    public void setMatchers() {
        matchers.set(0, App.getCommandMatcher(input, "^\\s*select\\s+card\\s+(?<num>\\d+)\\s+player\\s+(?<player>\\d+)\\s*$"));
        matchers.set(1, App.getCommandMatcher(input, "^\\s*place\\s+card\\s+(?<num>\\d+)\\s+in\\s+(?<block>\\d+)\\s*$"));

    }

    @Override
    public void check() throws SQLException, IOException, ClassNotFoundException {

        if (matchers.get(0).find()) {
            Matcher matcher = matchers.get(0);
            System.out.println(gameLauncherController.selectCard(Integer.parseInt(matcher.group("player")), Integer.parseInt(matcher.group("num"))));
        }

        if (matchers.get(1).find()) {
            Matcher matcher = matchers.get(1);
            String output = gameLauncherController.placeCard(Integer.parseInt(matcher.group("num")), Integer.parseInt(matcher.group("block")));
            System.out.println(output);

            if (output.equals("Card was successfully placed in the position")) {
                System.out.println(gameLauncherController.printTheGame());
            }
        }

    }

    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        if (!app.getCurrentUser().hasReceivedStarterPack()) {
            System.out.println("Please receive your starter pack in the shop first.");
            return;
        }

        System.out.println("\n----------\nGAME START\n----------\n");
        System.out.println(gameLauncherController.printTheGame());
        run();

    }
}
