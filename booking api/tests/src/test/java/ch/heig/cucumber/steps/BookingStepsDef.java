package ch.heig.cucumber.steps;

import ch.heig.ApiException;
import ch.heig.cucumber.helpers.Environment;
import ch.heig.model.Booking;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created on 2020-01-30.
 *
 * @author Max
 */
public class BookingStepsDef {

    private Environment env;
    private Booking newBooking = new Booking().activityId(3).locationId(2).nbPlaces(5).date("20211202");


    public BookingStepsDef(Environment env) {
        this.env = env;
    }


//    @Given("I made no bookings")
//    public void iMadeNoBookings() {
//
//    }

    @When("I send a GET request to \\/booking\\/my")
    public void iSendAGETRequestToBookingMy() {
        try {
            env.setLastResp(env.getBookApi().getBookingsWithHttpInfo(null, null));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp(null);
        }
    }

    @When("I send a POST request to \\/booking with a new Booking")
    public void iSendAPOSTRequestToBooking() {
        try {
            env.setLastResp(env.getBookApi().bookWithHttpInfo(newBooking));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp(null);
        }
    }

    @When("I send a POST request to \\/booking with too much places")
    public void iSendAPOSTRequestToBookingWithTooMuchPlaces() {
        try {
            Booking tooMuchPlaces = new Booking().activityId(3).locationId(2).nbPlaces(48).date("20211202");
            env.setLastResp(env.getBookApi().bookWithHttpInfo(tooMuchPlaces));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp(null);
        }
    }

    @When("I send a POST request to \\/booking with an invalid location ID")
    public void iSendAPOSTRequestToBookingWithAnInvalidLocationID() {
        try {
            Booking wrongId = new Booking().activityId(3).locationId(45).nbPlaces(5).date("20211202");
            env.setLastResp(env.getBookApi().bookWithHttpInfo(wrongId));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp(null);
        }
    }


    @When("I send a DELETE request to \\/booking\\/{int}")
    public void iSendADELETERequestToBooking(int id) {
        try {
            env.setLastResp(env.getBookApi().deleteBookingWithHttpInfo(id));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp(null);
        }
    }

    @Then("I should get an empty table")
    public void iShouldGetAnEmptyTable() {
        assertEquals(new ArrayList<>(), env.getLastResp().getData());
    }


    @Then("I should get my updated booking")
    public void iShouldGetMyUpdatedBooking() {
        assertEquals(Arrays.asList(newBooking.nbPlaces(10)
                                             .activityName("orienteering")
                                             .locationName("Nature sports")
                                             .date("2021-12-02")
                                             .id(1)) , env.getLastResp().getData());
    }
}
