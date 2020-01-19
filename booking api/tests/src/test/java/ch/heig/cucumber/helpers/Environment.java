package ch.heig.cucumber.helpers;


import java.io.IOException;
import java.util.Properties;
import ch.heig.api.*;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private ActivitiesApi api = new ActivitiesApi();

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("booking.server.url");
        api.getApiClient().setBasePath(url);

    }

    public ActivitiesApi getApi() {
        return api;
    }


}
