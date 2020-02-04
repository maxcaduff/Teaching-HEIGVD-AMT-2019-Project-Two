package io.swagger.Repositories;

import io.swagger.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created on 05.01.20.
 *
 * @author Max
 */
public interface LocationRepository extends CrudRepository<Location, Integer> {

  List<Location> findAll();
  Page<Location> findAll(Pageable page);
}
