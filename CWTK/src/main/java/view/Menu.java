package view;

import java.io.IOException;
import java.sql.SQLException;

public interface Menu {
    void run() throws SQLException, IOException, ClassNotFoundException;
    void setMatchers();
    void check() throws SQLException, IOException, ClassNotFoundException;
    void start() throws SQLException, IOException, ClassNotFoundException;
}
