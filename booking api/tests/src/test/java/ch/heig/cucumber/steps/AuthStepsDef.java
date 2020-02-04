package ch.heig.cucumber.steps;

import ch.heig.cucumber.helpers.Environment;
import io.cucumber.java.en.Given;

/**
 * Created on 2020-01-29.
 *
 * @author Max
 */
public class AuthStepsDef {

    private Environment env;

    public AuthStepsDef(Environment env) {
        this.env = env;
    }


    @Given("I have no valid token")
    public void iHaveNoValidToken() {
        env.getLocApi().getApiClient().addDefaultHeader("Authorization", "IN.VALID.TOKEN");
    }

    // the 3 tokens used here were specially generated with an exp value of 1618033989, which not only is phi, but also is a date in april 2021 so it should be enough for the tests ^^
    @Given("I am connected as a user")
    public void iAmConnectedAsAUser() {
        env.getLocApi().getApiClient().addDefaultHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2JAZHVwb250LmNoIiwiaXNzIjoiQXV0aEFwaSIsImV4cCI6MTYxODAzMzk4OX0.z0pJhMOfUxEdUwePwOXlVWoryy3ajzmWxTA86daHS5Q");
    }

    @Given("I am connected as another user")
    public void iAmConnectedAsAnotherUser() {
        env.getLocApi().getApiClient().addDefaultHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZWFuQG1pY2hlbC5jaCIsImlzcyI6IkF1dGhBcGkiLCJleHAiOjE2MTgwMzM5ODl9.Em0Rtj5vCKw1Q7FHJyllJF8-0Uydd0N6Se30-fe00mc");
    }

    @Given("I am connected as an admin")
    public void iAmConnectedAsAnAdmin() {
        env.getLocApi().getApiClient().addDefaultHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXhAaGVpZy5jaCIsImlzcyI6IkF1dGhBcGkiLCJleHAiOjE2MTgwMzM5ODl9.hsjQcRtKn5DqQCi5B7m0yX2ca5GStdIJEaALlxgHcRE");
    }

}
