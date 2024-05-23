package muhammadzainigunanda.com.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import muhammadzainigunanda.com.config.ConfigLoader;

public class DatabaseUtil {
     
     private static HikariDataSource hikariDataSource;

     static {
          // Configure application
          String propertiesFilePath = "src/main/resources/application.properties";
          ConfigLoader configLoader = new ConfigLoader(propertiesFilePath);

          HikariConfig configuration = new HikariConfig();

          // Configure Hikari
          configuration.setDriverClassName("com.mysql.cj.jdbc.Driver");
          configuration.setJdbcUrl("jdbc:mysql://" + configLoader.getProperty("database.host") + ":" +
                                  configLoader.getProperty("database.port") + "/" +
                                  configLoader.getProperty("database.name") +
                                  "?serverTimezone=Asia/Jakarta");
          configuration.setUsername(configLoader.getProperty("database.username"));
          configuration.setPassword(configLoader.getProperty("database.password"));

          // Pooling connections
          configuration.setMaximumPoolSize(10);
          configuration.setMinimumIdle(5);
          configuration.setIdleTimeout(60_000);
          configuration.setMaxLifetime(10 * 60_000);
          
          hikariDataSource = new HikariDataSource(configuration);
     }

     public static HikariDataSource getDataSource() {
          return hikariDataSource;
     }

}
