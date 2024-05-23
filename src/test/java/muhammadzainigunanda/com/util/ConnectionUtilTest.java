package muhammadzainigunanda.com.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectionUtilTest {
     
     @Test
     public void testConnection() throws SQLException {
          Connection connection = DatabaseUtil.getDataSource().getConnection();
          connection.close();
     }

     @Test
     public void testAccessDeniedConnection() {
          Assertions.assertThrowsExactly(ExceptionInInitializerError.class, () -> {
               Connection connection = DatabaseUtil.getDataSource().getConnection();
               connection.close();
          });
     }

}
