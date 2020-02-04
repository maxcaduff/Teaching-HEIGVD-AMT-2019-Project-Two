package ch.heig.cucumber.steps;

import ch.heig.ApiException;
import ch.heig.ApiResponse;
import ch.heig.cucumber.helpers.Environment;
import ch.heig.model.Location;
import io.cucumber.java.en.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 19.01.20.
 *
 * @author Max
 */
public class LocationStepsDef {



    private Environment env;

    public LocationStepsDef(Environment env) {
        this.env = env;
    }


    @When("I send a GET request to \\/location\\/all")
    public void iSendAGETRequestTo_location_all() {
        try {
            env.setLastResp( env.getLocApi().getLocationsWithHttpInfo(0));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp( null);
        }
    }


    @When("I send a POST request to \\/location with a new location")
    public void iSendAPOSTRequestTo_locationWithANewLocation() {
        try {
            env.setLastResp( env.getLocApi().addLocationWithHttpInfo(new Location().address("new building 42, 1618 Modern City").name("new Sports Club")));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp( null);
        }
    }

    @When("I send a POST request to \\/location with a malformed location")
    public void iSendAPOSTRequestTo_locationWithAMalformedLocation() {
        try {
            env.setLastResp( env.getLocApi().addLocationWithHttpInfo(new Location().name("my").address("is too short")));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp( null);
        }
    }

    @When("I send a PUT request to \\/location with an edited location for id = {int}")
    public void iSendAPUTRequestTo_locationWithAnEditedLocation(int locId) {
        try {
            env.setLastResp( env.getLocApi().updateLocationWithHttpInfo(locId, new Location().address("new building 42, 1618 Modern City").name("Shark sports")));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp( null);
        }
    }

    @When("I send a DELETE request to \\/location for id = {int}")
    public void iSendADELETERequestTo_location(int locId) {
        try {
            env.setLastResp( env.getLocApi().deleteLocationWithHttpInfo(locId));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp( null);
        }
    }

    @Then("I should get the first page of locations")
    public void iShouldGetTheFirstPageOfLocations() {

        assertNull("api error", env.getLastExcept());
        assertNotNull("no response", env.getLastResp());
        assertEquals(env.getDefaultLocations(), env.getLastResp().getData());
    }

//
//    @Then("The status code is {int}")
//    public void theStatusCodeIs(int code) {
//        if (env.getLastResp() != null)
//            assertEquals(code, env.getLastResp().getStatusCode());
//        else
//            assertEquals(code, env.getLastExcept().getCode());
//    }

}
