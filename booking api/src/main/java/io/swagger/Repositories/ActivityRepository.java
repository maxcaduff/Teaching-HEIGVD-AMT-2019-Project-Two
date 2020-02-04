package io.swagger.Repositories;

import io.swagger.model.Activity;
import io.swagger.model.ActivityResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 05.01.20.
 *
 * @author Max
 */
public interface ActivityRepository extends CrudRepository<Activity, Integer> {

    Page<Activity> findAll(Pageable page);
    List<Activity> findAll();



    // shit's not workin'
    //@Query(value = "SELECT a, l, a.maxPlaces - SUM((SELECT b.nb_places FROM BookingEntity b where a.id = b.activity AND l.id = b.location AND b.date = ?1 )) as placesLeft FROM Activity a, Location l")
    @Query(value = "SELECT a From Activity a, BookingEntity b WHERE b.date = :date")
    List<ActivityResult> findAvailableOn(@Param("date") String date, Pageable page);

    //@Query(value = "SELECT a, l, a.maxPlaces - SUM((SELECT b.nb_places FROM BookingEntity b where a.id = b.activity AND ?2 = b.location AND b.date = ?1 )) as placesLeft FROM Activity a, Location l where l.id = ?2")
    @Query(value = "SELECT a, l from Activity a, Location l, BookingEntity b Where b.date = :date AND b.location = :loc")
    List<ActivityResult> findAvailableOnDay(@Param("date") String date, @Param("loc") int locationId, Pageable page);

}
