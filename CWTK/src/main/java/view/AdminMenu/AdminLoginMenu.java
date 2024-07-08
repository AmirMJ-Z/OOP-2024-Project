package view.AdminMenu;

import controller.MainController;
import model.Admin;
import model.App;
import model.Captcha;
import view.Main;
import view.MiniMenu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminLoginMenu implements MiniMenu {
    private String input;
    private App app = App.getInstance();
    private Scanner s;
    private Captcha captcha;
    private Admin admin = Admin.getInstance();
    private MainController mainController = new MainController();

    public AdminLoginMenu() {
        s = new Scanner(System.in);
    }
    @Override
    public void run() throws SQLException, IOException, ClassNotFoundException {
        input = "";

        while (!admin.getPassword().checkPassword(input)) {
            System.out.println("Admin Password:");
            input = s.nextLine();
        }

        System.out.println("Access Granted");
        System.out.println("Welcome ADMIN!");
        AdminMenu adminMenu = new AdminMenu();
        adminMenu.start();
    }
    @Override
    public void start() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("\n-----------\nADMIN LOGIN\n-----------\n");
        run();
    }
}
