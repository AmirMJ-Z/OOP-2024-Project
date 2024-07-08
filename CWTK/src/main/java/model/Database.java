package model;

import model.Card.Card;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class Database{
    private String address;
    private Connection connection;
    private PreparedStatement addUserStatement;
    private PreparedStatement getUserStatement;
    private PreparedStatement addGameStatement;
    private PreparedStatement getGameStatement;
    private PreparedStatement updateUserStatement;
    private PreparedStatement addCardStatement;
    private PreparedStatement getCardByNameStatement;
    private PreparedStatement getAllCardsStatement;
    private PreparedStatement deleteCardStatement;
    private PreparedStatement getAllUserStatement;
    private PreparedStatement updateCardStatement;
    private static Database database;

    static {
        try {
            database = new Database("jdbc:sqlite:C:\\Users\\Amirr\\Main Server\\Java Projects\\CWTK\\src\\main\\resources\\Database\\MainDatabase.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Database getInstance() {
        return database;
    }

    private Database(String address) throws SQLException {
        this.address = address;
        connection = DriverManager.getConnection(address);
        addUserStatement = connection.prepareStatement("INSERT INTO Users VALUES (?, ?);");
        getUserStatement = connection.prepareStatement("SELECT Object FROM Users WHERE Username=?;");
        addGameStatement = connection.prepareStatement("INSERT INTO Games VALUES (?, ?, ?);");
        getGameStatement = connection.prepareStatement("SELECT Object FROM Games WHERE Player1 = ? OR Player2 = ?;");
        updateUserStatement = connection.prepareStatement("UPDATE Users SET Object = ? WHERE Username = ?;");
        addCardStatement = connection.prepareStatement("INSERT INTO Cards VALUES (?, ?);");
        getCardByNameStatement = connection.prepareStatement("SELECT Object FROM Cards WHERE Name = ?;");
        getAllCardsStatement = connection.prepareStatement("SELECT Object FROM Cards;");
        deleteCardStatement = connection.prepareStatement("DELETE FROM Cards WHERE Name = ?;");
        getAllUserStatement = connection.prepareStatement("SELECT Object FROM Users ORDER BY Username;");
        updateCardStatement = connection.prepareStatement("UPDATE Cards SET Object = ? WHERE Name = ?;");
    }

    public User getUserByUsername(String username) throws SQLException, IOException, ClassNotFoundException {
        getUserStatement.setString(1, username);
        ResultSet rs = getUserStatement.executeQuery();
        rs.next();

        byte[] buf = rs.getBytes(1);

        if (buf == null) {
            return null;
        }

        User user = SerializationUtils.deserialize(buf);

        return user;
    }

    public void addUser(User user) throws SQLException {
        byte[] blob = SerializationUtils.serialize(user);
        addUserStatement.setString(1, user.getUsername());
        addUserStatement.setBytes(2, blob);
        addUserStatement.execute();
    }

    public void addGame(Game game) throws SQLException {
        byte[] blob = SerializationUtils.serialize(game);
        addGameStatement.setString(1, game.getUser1().getUsername());
        addGameStatement.setString(2, game.getUser2().getUsername());
        addGameStatement.setBytes(3, blob);
        addGameStatement.execute();
    }

    public ArrayList<Game> getGameByUsername(String username) throws SQLException {
        ArrayList<Game> games = new ArrayList<>();

        getGameStatement.setString(1, username);
        getGameStatement.setString(2, username);
        ResultSet rs = getGameStatement.executeQuery();

        while (rs.next()) {
            byte[] buf = rs.getBytes(1);

            if (buf == null) {
                return null;
            }

            Game game = SerializationUtils.deserialize(buf);
            games.add(game);
        }

        return games;
    }

    public void updateUser(User user) throws SQLException {
        byte[] blob = SerializationUtils.serialize(user);
        updateUserStatement.setString(2, user.getUsername());
        updateUserStatement.setBytes(1, blob);
        updateUserStatement.execute();
    }

    public void addCard(Card card) throws SQLException {
        byte[] blob = SerializationUtils.serialize(card);
        addCardStatement.setString(1, card.getName());
        addCardStatement.setBytes(2, blob);
        addCardStatement.execute();
    }

    public Card getCardByName(String name) throws SQLException {
        getCardByNameStatement.setString(1, name);
        ResultSet rs = getCardByNameStatement.executeQuery();
        rs.next();

        byte[] buf = rs.getBytes(1);

        if (buf == null) {
            return null;
        }

        Card card = SerializationUtils.deserialize(buf);

        return card;
    }

    public ArrayList<Card> getAllCards() throws SQLException {
        ArrayList<Card> cards = new ArrayList<>();

        ResultSet rs = getAllCardsStatement.executeQuery();

        while (rs.next()) {
            byte[] buf = rs.getBytes(1);

            if (buf == null) {
                return null;
            }

            Card card = SerializationUtils.deserialize(buf);
            cards.add(card);
        }

        return cards;
    }

    public void deleteCard(Card card) throws SQLException {
        deleteCardStatement.setString(1, card.getName());
        deleteCardStatement.execute();
    }

    public ArrayList<User> getAllPlayers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        ResultSet rs = getAllUserStatement.executeQuery();

        while (rs.next()) {
            byte[] buf = rs.getBytes(1);

            if (buf == null) {
                return null;
            }

            User user = SerializationUtils.deserialize(buf);
            users.add(user);
        }

        return users;
    }

    public void updateCard(Card card) throws SQLException {
        byte[] blob = SerializationUtils.serialize(card);
        updateCardStatement.setString(2, card.getName());
        updateCardStatement.setBytes(1, blob);
        updateCardStatement.execute();
    }


}
