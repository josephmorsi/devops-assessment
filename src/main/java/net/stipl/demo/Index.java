package net.stipl.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mysql.jdbc.jdbc2.optional.*;
import java.sql.*;

@RestController
public class Index {

  @RequestMapping("/")
  public String index() {
    String ret = "";

    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUser(System.getenv("DB_USER"));
    dataSource.setPassword(System.getenv("DB_PASSWORD"));
    dataSource.setServerName(System.getenv("DB_HOST"));
    dataSource.setDatabaseName(System.getenv("DB_NAME"));

    try (Connection conn = dataSource.getConnection()) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM test");

      ret = (System.getenv("HOSTNAME") + ": Hello, I'm " + System.getenv("APP_VERSION"));

      while (rs.next()) {
        ret += ("\nDB: " + rs.getString(1));
      }

      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException("Error connecting to DB");
    }
    return ret;
  }

}
