package view;

import model.*;
import model.Card.Card;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
//        App app = App.getInstance();
//        app.run();

        Database database = Database.getInstance();

        database.addCard(new Card("Mercury", 56, 31, 2, 200, 1, 10, CardType.Armstrong));
        database.addCard(new Card("Venus", 51, 21, 3, 180, 1,9 , CardType.Armstrong));
        database.addCard(new Card("Sun", 34, 30, 1, 160, 1, 8, CardType.Armstrong));
        database.addCard(new Card("Sirius", 44, 24, 4, 160, 1, 8, CardType.Armstrong));
        database.addCard(new Card("Earth", 33, 33, 1, 160, 1, 8, CardType.Armstrong));
        database.addCard(new Card("Mars", 35, 35, 5, 140, 1, 7, CardType.Aldrin));
        database.addCard(new Card("Alpha Centauri", 45, 30, 3, 150, 1, 7, CardType.Aldrin));
        database.addCard(new Card("Betelgeuse ", 7, 30, 1, 100, 1, 4, CardType.Aldrin));
        database.addCard(new Card("Jupiter", 26, 30, 2, 120, 1, 4, CardType.Aldrin));
        database.addCard(new Card("Saturn", 25, 30, 5, 90, 1, 3, CardType.Aldrin));
        database.addCard(new Card("Rigel", 15, 27, 3, 80, 1, 2, CardType.Glenn));
        database.addCard(new Card("Uranus", 45, 36, 3, 160, 1, 7, CardType.Glenn));
        database.addCard(new Card("Neptune", 34, 30, 1, 160, 1, 7, CardType.Glenn));
        database.addCard(new Card("Vega", 36, 30, 1, 170, 1, 7, CardType.Glenn));
        database.addCard(new Card("Antares", 35, 27, 5, 150, 1, 6, CardType.Glenn));

        database.addCard(new Card("Pluto", 35, 20, 5, 120, 1, 5, CardType.Gagarin));
        database.addCard(new Card("Eiris", 15, 20, 5, 50, 1, 1, CardType.Gagarin));
        database.addCard(new Card("Aldebaran", 26, 30, 2, 130, 1, 4, CardType.Gagarin));
        database.addCard(new Card("Polaris", 33, 30, 3, 150, 1, 4, CardType.Gagarin));
        database.addCard(new Card("Haumea ", 55, 30, 5, 160, 1, 3, CardType.Gagarin));


    }
}