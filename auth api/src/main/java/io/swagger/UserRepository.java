package io.swagger;

import io.swagger.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created on 05.01.20.
 *
 * @author Max
 */
public interface UserRepository extends CrudRepository<User, String> {

    boolean existsUserByEmailAndPasswordEquals(String email, String password);

}
