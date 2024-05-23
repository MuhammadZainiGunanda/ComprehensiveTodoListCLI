package muhammadzainigunanda.com.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

     private Properties properties;

     public ConfigLoader(String propertiesFilePath) {
          properties = new Properties();
          try (var inputStream = new FileInputStream(propertiesFilePath)) {
               properties.load(inputStream);
          } catch (IOException exception) {
               throw new RuntimeException("Failed to load properties from " + propertiesFilePath, exception);
          }
     }

     public String getProperty(String key) {
          return properties.getProperty(key);
     }
     
}
