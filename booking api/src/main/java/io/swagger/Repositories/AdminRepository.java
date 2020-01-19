package io.swagger.Repositories;

import io.swagger.model.Admin;
import org.springframework.data.repository.CrudRepository;

/**
 * Created on 18.01.20.
 *
 * @author Max
 */
public interface AdminRepository extends CrudRepository<Admin, String> {

}
