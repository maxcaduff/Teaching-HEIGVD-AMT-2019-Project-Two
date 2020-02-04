package io.swagger.api;

import io.swagger.Repositories.ActivityRepository;
import io.swagger.Repositories.BookingRepository;
import io.swagger.Repositories.LocationRepository;
import io.swagger.model.Activity;
import io.swagger.model.Booking;
import io.swagger.annotations.*;
import io.swagger.model.BookingEntity;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-01-05T20:58:36.237Z[GMT]")
@Controller
public class BookingApiController implements BookingApi {

    private static final Logger log = LoggerFactory.getLogger(BookingApiController.class);

    private final HttpServletRequest request;

    private final BookingRepository bookingRepository;
    private final ActivityRepository activityRepository;
    private final LocationRepository locationRepository;

    private final int pageSize = 5;

    @Autowired
    public BookingApiController( HttpServletRequest request, BookingRepository bookRepo, ActivityRepository actRepo, LocationRepository locRepo) {
        this.request = request;
        this.bookingRepository = bookRepo;
        this.activityRepository = actRepo;
        this.locationRepository = locRepo;
    }

    public ResponseEntity<Void> book(@ApiParam(value = "Booking infos", required=true )  @Valid @RequestBody Booking body ) {
        String user = (String) request.getAttribute("email");

        Activity activity = activityRepository.findOne(body.getActivityId());
        boolean locExists = locationRepository.exists(body.getLocationId());

        if (activity == null || ! locExists)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        List<BookingEntity> bookings = bookingRepository.getAllByActivityAndLocationAndDate(body.getActivityId(), body.getLocationId(), body.getDate());
        int placesLeft = activity.getMaxPlaces() - bookings.stream().reduce(0, (sum, book) -> sum + book.getNbPlaces(), Integer::sum);
        if (placesLeft - body.getNbPlaces() < 0)
            return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);

        BookingEntity bookingToSave = null;
        for (BookingEntity b: bookings )
            if (b.getUser().equals(user)) {
                // adding places to existing, makes more sense than replacing or denying
                bookingToSave = b;
                bookingToSave.setNbPlaces(body.getNbPlaces() + b.getNbPlaces());
                break;
            }
        if (bookingToSave == null)
//            log.info("booking to save was null\n");
            bookingToSave = new BookingEntity(user, body);

        bookingRepository.save(bookingToSave);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<Void> deleteBooking(@ApiParam(value = "id of booking to delete", required=true) @PathVariable("id") Integer id ) {
        String user = (String) request.getAttribute("email");
        BookingEntity booking = bookingRepository.findOne(id);
        if (booking == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        if (! booking.getUser().equals(user))
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        bookingRepository.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<List<Booking>> getBookings(@ApiParam(value = "page wished, defaults to first page (0).") @Valid @RequestParam(value = "page", required = false) Integer page
            ,@ApiParam(value = "true to get previous bookings.") @Valid @RequestParam(value = "previous", required = false) Boolean previous ) {
        String accept = request.getHeader("Accept");
        String user = (String) request.getAttribute("email");

        if (accept != null && (accept.contains("application/json") || accept.contains("*/*"))) {

            if (page == null)
                page = 0;
            Pageable pageNb = new PageRequest(page, pageSize);
            if (previous == null) previous = false;

            String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
            List<BookingEntity> userBooking = (previous ?
                    bookingRepository.findAllByUserAndDateBefore(user, today, pageNb ).getContent()
                    : bookingRepository.findAllByUserAndDateGreaterThanEqual(user, today, pageNb).getContent() );
            List<Booking> resp = new ArrayList<>();
            for (BookingEntity b : userBooking)
                resp.add(new Booking(b, activityRepository.findOne(b.getActivity()).getName(), locationRepository.findOne(b.getlocation()).getName()));

            return new ResponseEntity<List<Booking>>( resp , HttpStatus.OK);
        }
        return new ResponseEntity<List<Booking>>(HttpStatus.NOT_ACCEPTABLE);
    }

}
