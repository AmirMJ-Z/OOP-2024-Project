package Model;

import javax.xml.crypto.Data;

public class Database {
    private static Database database = new Database();
    private Database() {};
    public static Database getInstance() {
        return database;
    }
}
