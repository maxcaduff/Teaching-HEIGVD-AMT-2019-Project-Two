/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.15).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Body;
import io.swagger.model.PublicUser;
import io.swagger.model.User;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-25T21:37:52.105Z[GMT]")
@Api(value = "user", description = "the user API")
public interface UserApi {

    @ApiOperation(value = "Add a new user. Password is generated and sent to specified email", nickname = "addUser", notes = "", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "User created successfully"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 500, message = "Error sending mail") })
    @RequestMapping(value = "/user",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addUser(@ApiParam(value = "New user to add", required=true )  @Valid @RequestBody PublicUser body
);


    @ApiOperation(value = "Delete the user linked to the provided JWT", nickname = "deleteUser", notes = "", authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Deleted successfully"),
        @ApiResponse(code = 404, message = "User not found"),
        @ApiResponse(code = 401, message = "Auth token required") })
    @RequestMapping(value = "/user",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUser();


    @ApiOperation(value = "Gets public infos of specified user", nickname = "getUser", notes = "", response = PublicUser.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success", response = PublicUser.class),
        @ApiResponse(code = 404, message = "User not found"),
        @ApiResponse(code = 406, message = "Bad accept header") })
    @RequestMapping(value = "/user",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PublicUser> getUser(@NotNull @ApiParam(value = "Email of the user to fetch", required = true) @Valid @RequestParam(value = "email", required = true) String email
);


    @ApiOperation(value = "login and get JWT", nickname = "login", notes = "Returns a JWT if credentials match", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "successful login"),
        @ApiResponse(code = 400, message = "Invalid credentials supplied") })
    @RequestMapping(value = "/user/login",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> login(@ApiParam(value = "User credentials" ,required=true )  @Valid @RequestBody Body body
);


    @ApiOperation(value = "Update the user linked to the provided JWT", nickname = "updateUser", notes = "", authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Updated successfully"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 401, message = "Auth token required"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/user",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateUser(@ApiParam(value = "New values for user", required=true ) @Valid @RequestBody User body
);

    @ApiOperation(value = "reset password", nickname = "reset", notes = "sends a new password to specified email", tags={ "user", })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "successful reset"),
            @ApiResponse(code = 400, message = "No email given"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Error sending password by email") })
    @RequestMapping(value = "/user/newPassword",
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Void> reset(@ApiParam(value = "email to reset", required=true ) @Valid @RequestBody Body body
    );


}
