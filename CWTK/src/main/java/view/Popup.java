package view;

import java.io.IOException;
import java.sql.SQLException;

public interface Popup {
    boolean run() throws SQLException, IOException, ClassNotFoundException;
    boolean start() throws SQLException, IOException, ClassNotFoundException;
}
