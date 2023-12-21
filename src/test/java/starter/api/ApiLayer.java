package starter.api;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static io.restassured.RestAssured.given;
import static starter.Utils.PropertiesLoader.loadProperties;

/**
 * ApiLayer class - holds the variables and methods for handling the API requests
 * This class can be extended with various other methods for API calls
 */
public class ApiLayer {

    public Response response;
    public Properties properties = loadProperties();
    public static final Logger LOGGER = LoggerFactory.getLogger(ApiLayer.class);

    /**
     * Wrapper for GET method from Serenity Rest
     *
     * @param url    - the url for request
     * @param params - list of query params for get (optional)
     */
    public void get(String url, Object... params) {

        if (properties.getProperty("requestLogging").equals("true")) {
            LOGGER.info("URL: " + url);
            response = given().log().all().get(url, params).then().log().all().and().extract().response();
        } else {
            response = given().get(url, params);
        }
        if (properties.getProperty("logResponsesAsString").equals("true")) {
            LOGGER.info(response.asString());
        }
    }

}
