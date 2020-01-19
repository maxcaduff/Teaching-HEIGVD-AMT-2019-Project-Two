/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.16).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Activity;
import io.swagger.model.ActivityResult;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-01-05T20:58:36.237Z[GMT]")
@Api(value = "activity", description = "the activity API")
public interface ActivityApi {

    @ApiOperation(value = "Add a new activity", nickname = "addActivity", notes = "", authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "activities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Activity created successfully"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 401, message = "Auth token required"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/activity",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addActivity(@ApiParam(value = "New activity to add" ,required=true )  @Valid @RequestBody Activity body
);


    @ApiOperation(value = "Delete the specified activity", nickname = "deleteActivity", notes = "", authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "activities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Deleted successfully"),
        @ApiResponse(code = 401, message = "Auth token required"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Id not found") })
    @RequestMapping(value = "/activity/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteActivity(@ApiParam(value = "id of activity to delete.",required=true) @PathVariable("id") Integer id
);


    @ApiOperation(value = "Get all activities", nickname = "getActivities", notes = "", response = Activity.class, responseContainer = "List", tags={ "activities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success", response = Activity.class, responseContainer = "List"),
        @ApiResponse(code = 406, message = "Bad accept header") })
    @RequestMapping(value = "/activity/all",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Activity>> getActivities(@ApiParam(value = "page wished, defaults to first page (0).") @Valid @RequestParam(value = "page", required = false) Integer page
);


    @ApiOperation(value = "Get the number of places left for all activities on a certain day", nickname = "getAvailableActivities", notes = "", response = ActivityResult.class, responseContainer = "List", tags={ "activities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success", response = ActivityResult.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "wrong format"),
        @ApiResponse(code = 406, message = "Bad accept header")})
    @RequestMapping(value = "/activity/availability/{day}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<ActivityResult>> getAvailableActivities(@ApiParam(value = "selected day (YYYYMMDD).",required=true) @PathVariable("day") String day
,@ApiParam(value = "page wished, defaults to first page (0).") @Valid @RequestParam(value = "page", required = false) Integer page
,@ApiParam(value = "Location ID to filter results.") @Valid @RequestParam(value = "loc", required = false) Integer loc
);


    @ApiOperation(value = "Update an activity, id must exist", nickname = "updateActivity", notes = "", authorizations = {
        @Authorization(value = "bearerAuth")    }, tags={ "activities", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Updated successfully"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 401, message = "Auth token required"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Id not found") })
    @RequestMapping(value = "/activity/{id}",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateActivity(@ApiParam(value = "New values for activity, id not needed here" ,required=true )  @Valid @RequestBody Activity body
,@ApiParam(value = "id of activity to edit.",required=true) @PathVariable("id") Integer id
);

}
