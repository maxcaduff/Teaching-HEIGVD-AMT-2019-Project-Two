package io.swagger.api;

import io.swagger.Repositories.LocationRepository;
import io.swagger.model.Location;
import io.swagger.annotations.*;
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
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-01-05T20:58:36.237Z[GMT]")
@Controller
public class LocationApiController implements LocationApi {

    private static final Logger log = LoggerFactory.getLogger(LocationApiController.class);

    private final HttpServletRequest request;

    private final LocationRepository locationRepository;
    private final int pageSize = 5;

    @Autowired
    public LocationApiController(HttpServletRequest request, LocationRepository locationRepository) {
        this.request = request;
        this.locationRepository = locationRepository;
    }

    public ResponseEntity<Void> addLocation(@ApiParam(value = "New location to add", required=true) @Valid @RequestBody Location body ) {
        locationRepository.save(body);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Void> deleteLocation(@ApiParam(value = "id of location to delete", required=true) @PathVariable("id") Integer id ) {
        Location loc = locationRepository.findOne(id);
        if (loc == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        locationRepository.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<Location>> getLocations(@ApiParam(value = "page wished, defaults to first page (0).") @Valid @RequestParam(value = "page", required = false) Integer page ) {
        String accept = request.getHeader("Accept");
        if (accept != null && ( accept.contains("application/json") || accept.contains("*/*"))) {
            if (page == null)
                page = 0;
            Pageable pageNb = new PageRequest(page, pageSize);
            return new ResponseEntity<List<Location>>(locationRepository.findAll(pageNb).getContent(), HttpStatus.OK);
        }
        return new ResponseEntity<List<Location>>(HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<Void> updateLocation(@ApiParam(value = "New values for location", required=true )  @Valid @RequestBody Location body
            ,@ApiParam(value = "id of location to edit.",required=true) @PathVariable("id") Integer id ) {
        Location loc = locationRepository.findOne(id);
        if (loc == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        locationRepository.save(body);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
