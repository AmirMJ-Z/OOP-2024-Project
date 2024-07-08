package view;

import java.io.IOException;
import java.sql.SQLException;

public interface MiniMenu {
    void run() throws SQLException, IOException, ClassNotFoundException;
    void start() throws SQLException, IOException, ClassNotFoundException;
}
