package ch.heig.cucumber.helpers;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import ch.heig.ApiException;
import ch.heig.ApiResponse;
import ch.heig.api.ActivitiesApi;
import ch.heig.api.BookingsApi;
import ch.heig.api.LocationsApi;
import ch.heig.model.Activity;
import ch.heig.model.Location;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private ActivitiesApi actApi = new ActivitiesApi();
    private LocationsApi locApi = new LocationsApi();
    private BookingsApi bookApi = new BookingsApi();

    private ApiResponse lastResp;
    private ApiException lastExcept;

    private List<Activity> defaultActivities = Arrays.asList(new Activity().id(1).name("Rafting").description("come try rafting! Bring your swimwear.").maxPlaces(12),
                                                             new Activity().id(2).name("Paint ball").description("Paintball with all equipment provided").maxPlaces(40),
                                                             new Activity().id(3).name("orienteering").description("be the first!").maxPlaces(50));

    List<Location> defaultLocations = Arrays.asList(new Location().id(1).name("Green Valley").address("leaf road 12, 4242 Tree City"),
                                                    new Location().id(2).name("Nature sports").address("fit road 25, 1000 Main City"));




    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("booking.server.url");
        // the ApiClient is the same for all apis, setting it once does the job for the 3 of them.
        actApi.getApiClient().setBasePath(url);
    }

    public ActivitiesApi getActApi() {
        return actApi;
    }

    public LocationsApi getLocApi() {
        return locApi;
    }

    public BookingsApi getBookApi() {
        return bookApi;
    }

    public ApiResponse getLastResp() {
        return lastResp;
    }

    public void setLastResp(ApiResponse lastResp) {
        this.lastResp = lastResp;
    }

    public ApiException getLastExcept() {
        return lastExcept;
    }

    public void setLastExcept(ApiException lastExcept) {
        this.lastExcept = lastExcept;
    }

    public List<Activity> getDefaultActivities() {
        return defaultActivities;
    }

    public List<Location> getDefaultLocations() {
        return defaultLocations;
    }
}
