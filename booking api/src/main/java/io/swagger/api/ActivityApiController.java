package io.swagger.api;

import io.swagger.Repositories.ActivityRepository;
import io.swagger.Repositories.BookingRepository;
import io.swagger.Repositories.LocationRepository;
import io.swagger.model.Activity;
import io.swagger.model.ActivityResult;
import io.swagger.annotations.*;
import io.swagger.model.BookingEntity;
import io.swagger.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-01-05T20:58:36.237Z[GMT]")
@Controller
public class ActivityApiController implements ActivityApi {

    private static final Logger log = LoggerFactory.getLogger(ActivityApiController.class);
    private final HttpServletRequest request;

    private final int pageSize = 5;

    private final ActivityRepository activityRepository;
    private final LocationRepository locationRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public ActivityApiController(HttpServletRequest request, ActivityRepository actR, LocationRepository locR, BookingRepository bookR) {
        this.request = request;
        this.activityRepository = actR;
        this.locationRepository = locR;
        this.bookingRepository = bookR;
    }

    public ResponseEntity<Void> addActivity(@ApiParam(value = "New activity to add" ,required=true )  @Valid @RequestBody Activity body) {

        activityRepository.save(body);
        //log.info("body: "+ body + "\nrepo: " + activityRepository.findAll());
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Void> deleteActivity(@ApiParam(value = "id of activity to delete.",required=true) @PathVariable("id") Integer id ) {
        Activity act = activityRepository.findOne(id);
        if (act == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        activityRepository.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<Activity>> getActivities(@ApiParam(value = "page wished, defaults to first page (0).") @Valid @RequestParam(value = "page", required = false) Integer page ) {
        String accept = request.getHeader("Accept");
        if (accept != null && (accept.contains("application/json") || accept.contains("*/*"))) {

            if (page == null)
                page = 0;
            Pageable pageNb = new PageRequest(page, pageSize);

            return new ResponseEntity<List<Activity>>(activityRepository.findAll(pageNb), HttpStatus.OK);
        }
        return new ResponseEntity<List<Activity>>(HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<List<ActivityResult>> getAvailableActivities(@ApiParam(value = "selected day (YYYYMMDD).",required=true) @PathVariable("day") String day
            ,@ApiParam(value = "page wished, defaults to first page (0).") @Valid @RequestParam(value = "page", required = false) Integer page
            ,@ApiParam(value = "Location ID to filter results.") @Valid @RequestParam(value = "loc", required = false) Integer loc ) {

        String accept = request.getHeader("Accept");
        if (accept != null && (accept.contains("application/json") || accept.contains("*/*"))) {

            if (! day.matches("\\d{8}"))
                return new ResponseEntity<List<ActivityResult>>(HttpStatus.BAD_REQUEST);
            if (page == null)
                page = 0;
            int skip = pageSize * page;

            //log.info("params - loc: " + loc +" date: "+ day);
            
            List<Activity> allAct = activityRepository.findAll();
            List<BookingEntity> allBookings = bookingRepository.findAllByDate(day);

            List<ActivityResult> results = new LinkedList<>();

            // list of either all locations or the selected one
            List<Location> allLocs;
            if (loc == null)
                allLocs = locationRepository.findAll() ;
            else {
                allLocs = new ArrayList<Location>();
                allLocs.add(locationRepository.findOne(loc));
            }

            // this could be more efficient by calculating the total bookings for each couple with one pass on the
            // booking list with the help of a map, but it would anyway need multiple passes
            // on the activities and locations lists to find their infos based on the ids; and since we
            // send pages of results the load is not too heavy.
            outer:
            for (Activity a : allAct) {
                for (Location l : allLocs) {
                    if (skip --  > 0) continue;
                    int totBookings = allBookings.stream().reduce(0, (sum, book) -> (
                                (book.getActivity() == a.getId() && book.getlocation() == l.getId()) ?
                                 sum + book.getNbPlaces() : sum )  , Integer::sum);
                    int placesLeft = a.getMaxPlaces()-totBookings;
                    results.add(new ActivityResult(a,l,placesLeft));
                    if (results.size() == pageSize) break outer;
                }
            }
            return new  ResponseEntity<List<ActivityResult>>(results, HttpStatus.OK);

//            if (loc == null)
//                return new ResponseEntity<List<ActivityResult>>(activityRepository.findAvailableOn(day, pageNb), HttpStatus.OK);
//            return  new ResponseEntity<List<ActivityResult>>(activityRepository.findAvailableOnDay(day, loc, pageNb), HttpStatus.OK);
        }
        return new ResponseEntity<List<ActivityResult>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateActivity(@ApiParam(value = "New values for activity, id not needed here", required=true ) @Valid @RequestBody Activity body
            ,@ApiParam(value = "id of activity to edit.",required=true) @PathVariable("id") Integer id ) {
        Activity act = activityRepository.findOne(id);
        if (act == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        activityRepository.save(body);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
