package io.swagger.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created on 18.01.20.
 *
 * @author Max
 */
@Entity
public class Admin {

    @Id
    private String email = null;

    public Admin email(String email) {
        this.email = email;
        return this;
    }
    /**
     * Get id
     * @return id
     **/
    public String getEmail() {
        return email;
    }

    public void setId(String email) {
        this.email = email;
    }

}
