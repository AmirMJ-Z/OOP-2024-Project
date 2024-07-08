package controller;

import model.App;
import view.*;
import view.AdminMenu.AdminLoginMenu;

import java.io.IOException;
import java.sql.SQLException;

public class MainController {
    public MainController() {
        this.app = App.getInstance();
    }
    private final App app;
    public void loginMenu() throws SQLException, IOException, ClassNotFoundException {
        LoginMenu loginMenu = new LoginMenu();
//        app.setCurrentMenu(loginMenu);
        loginMenu.start();
    }

    public void signUpMenu() throws SQLException, IOException, ClassNotFoundException {
        SignupMenu signupMenu = new SignupMenu();
//        app.setCurrentMenu(signupMenu);
        signupMenu.start();
    }

    public void back() throws SQLException, IOException, ClassNotFoundException {
        MainMenu mainMenu = new MainMenu();
//        app.setCurrentMenu(mainMenu);
        mainMenu.start();
    }

    public void profileMenu() throws SQLException, IOException, ClassNotFoundException {
        ProfileMenu profileMenu = new ProfileMenu();
//        app.setCurrentMenu(mainMenu);
        profileMenu.start();
    }

    public void adminMenu() throws SQLException, IOException, ClassNotFoundException {
        AdminLoginMenu adminLoginMenu = new AdminLoginMenu();
//        app.setCurrentMenu(adminLoginMenu);
        adminLoginMenu.start();
    }

    public void scoreBoard() throws SQLException, IOException, ClassNotFoundException {
        ScoreBoard scoreBoard = new ScoreBoard();
//        app.setCurrentMenu(scoreBoard);
        scoreBoard.start();
    }

    public void gameMenu() throws SQLException, IOException, ClassNotFoundException {
        GameMenu gameMenu = new GameMenu();
//        app.setCurrentMenu(gameMenu);
        gameMenu.start();
    }
}
