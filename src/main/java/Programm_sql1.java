import static junit.framework.TestCase.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Programm_sql1 {

  static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/dbMylnychenko";
  static final String DB_USER = "root";
  static final String DB_PASSWORD = "13856889";
  static Connection conn;

  public static void main(String[] args) {

  }

  private Connection getNewConnection() throws SQLException {

    return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
  }

  @Before
  public void init() throws SQLException {
    conn = getNewConnection();
  }

  @After
  public void close() throws SQLException {
    conn.close();
  }

  private int executeUpdate(String query) throws SQLException {
    Statement statement = conn.createStatement();
    // Для Insert, Update, Delete
    int result = statement.executeUpdate(query);
    return result;
  }

  @Test
  public void shouldSelectData() throws SQLException {
    String query = "SELECT * FROM Clients WHERE name = ?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setString(1, "Brian");
    boolean hasResult = statement.execute();
    assertTrue(hasResult);
  }
}
