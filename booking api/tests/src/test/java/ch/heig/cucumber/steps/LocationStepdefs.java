package ch.heig.cucumber.steps;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

import static org.junit.Assert.assertEquals;

/**
 * Created on 19.01.20.
 *
 * @author Max
 */
public class LocationStepdefs {

    @Given("I am not connected")
    public void i_am_not_connected() {

    }

    @When("I send a GET request to /locations/all")
    public void i_send_a_GET_request_to_locations_all() {

    }

    @Then("I should get the first page of notifications and a 200 code.")
    public void i_should_get_a_status_code(int code) {
        assertEquals(code, 200);
    }
}
