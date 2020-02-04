package ch.heig.cucumber.steps;

import ch.heig.ApiException;
import ch.heig.cucumber.helpers.Environment;
import ch.heig.model.Activity;
import ch.heig.model.ActivityResult;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 19.01.20.
 *
 * @author Max
 */
public class ActivityStepsDef {


    private Activity newAct = new Activity().name("water polo").description("come take a bath").maxPlaces(17);

    private Environment env;

    public ActivityStepsDef(Environment env) {
        this.env = env;
    }


    @When("I send a GET request to \\/activity\\/all")
    public void iSendAGETRequestTo_activity_all() {
        try {
            env.setLastResp(env.getActApi().getActivitiesWithHttpInfo(null));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp(null);
        }
    }

    @When("I send a GET request to \\/activity\\/availability\\/{int} for page {int}")
    public void iSendAGETRequestToActivityAvailability(int date, int page) {
        try {
            env.setLastResp(env.getActApi().getAvailableActivitiesWithHttpInfo(String.valueOf(date), page, null));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp(null);
        }
    }


    @When("I send a POST request to \\/activity with a new activity")
    public void iSendAPOSTRequestTo_activityWithANewActivity() {
        try {
            env.setLastResp(env.getActApi().addActivityWithHttpInfo(newAct));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp(null);
        }
    }


    @When("I send a POST request to \\/activity with a malformed activity")
    public void iSendAPOSTRequestTo_activityWithAMalformedActivity() {
        try {
            env.setLastResp(env.getActApi().addActivityWithHttpInfo(new Activity().name("my").description("is too short")));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp(null);
        }
    }

    @When("I send a PUT request to \\/activity with an edited activity for id = {int}")
    public void iSendAPUTRequestTo_activityWithAnEditedActivity(int actId) {
        try {
            env.setLastResp(env.getActApi().updateActivityWithHttpInfo(actId, newAct.maxPlaces(12)));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp(null);
        }
    }

    @When("I send a DELETE request to \\/activity for id = {int}")
    public void iSendADELETERequestTo_activity(int actId) {
        try {
            env.setLastResp(env.getActApi().deleteActivityWithHttpInfo(actId));
            env.setLastExcept(null);
        } catch (ApiException e) {
            env.setLastExcept(e);
            env.setLastResp(null);
        }
    }

    @Then("I should get the first page of activities")
    public void iShouldGetTheFirstPageOfActivities() {

        assertNull("api error", env.getLastExcept());
        assertNotNull("no response", env.getLastResp());
        assertEquals(env.getDefaultActivities(), env.getLastResp().getData());

    }


    @Then("I should get the last page of activities results")
    public void iShouldGetTheLastPageOfActivitiesResults() {

        assertNull("api error", env.getLastExcept());
        assertNotNull("no response", env.getLastResp());
        List<ActivityResult> expected = Arrays.asList( new ActivityResult()
                .activity(env.getDefaultActivities().get(2))
                .location(env.getDefaultLocations().get(1))
                .placesLeft(env.getDefaultActivities().get(2).getMaxPlaces()));

        assertEquals(expected, env.getLastResp().getData());
    }

}
