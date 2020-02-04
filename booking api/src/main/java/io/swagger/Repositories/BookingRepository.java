package io.swagger.Repositories;

import io.swagger.model.BookingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created on 05.01.20.
 *
 * @author Max
 */
public interface BookingRepository extends CrudRepository<BookingEntity, Integer> {

    List<BookingEntity> findAllByDate(String date);

    List<BookingEntity> getAllByActivityAndLocationAndDate(int act, int loc, String date);

    Page<BookingEntity> findAllByUserAndDateGreaterThanEqual(String user, String date, Pageable page);

    Page<BookingEntity> findAllByUserAndDateBefore(String user, String date, Pageable page);



}
