package starter.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertiesLoader class - holds the variables and methods for loading and handling the configuration variables
 */
public class PropertiesLoader {
    /**
     * Properties file loader method
     * @return returns the properties object which contains various project parameters which can be customized as needed
     */
    public static Properties loadProperties() {
        Properties prop = new Properties();

        try (InputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")) {
            prop.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }
}
