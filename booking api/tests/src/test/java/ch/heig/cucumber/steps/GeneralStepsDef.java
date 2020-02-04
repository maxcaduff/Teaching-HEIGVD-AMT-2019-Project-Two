package ch.heig.cucumber.steps;

import ch.heig.ApiException;
import ch.heig.cucumber.helpers.Environment;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

/**
 * Created on 2020-01-30.
 *
 * @author Max
 */
public class GeneralStepsDef {

    private Environment env;


    public GeneralStepsDef(Environment env) {
        this.env = env;
    }


    @Then("The status code is {int}")
    public void theStatusCodeIs(int code) {
        if (env.getLastResp() != null)
            assertEquals(code, env.getLastResp().getStatusCode());
        else
            assertEquals(code, env.getLastExcept().getCode());
    }

}
