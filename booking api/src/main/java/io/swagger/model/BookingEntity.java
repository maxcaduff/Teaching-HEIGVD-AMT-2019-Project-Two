package io.swagger.model;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created on 05.01.20.
 *
 * @author Max
 */
@Entity
@Validated
@Table(name = "Booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    // user is retrieved from JWT
    private String user = null;

    @NotNull
    private Integer activity = null;

    @NotNull
    private Integer location = null;

    @NotNull
    private String date = null;

    @NotNull
    private Integer nbPlaces = null;


    public BookingEntity(String user, Booking b) {
        this.user = user;
        this.activity = b.getActivityId();
        this.location = b.getLocationId();
        this.date = b.getDate();
        this.nbPlaces = b.getNbPlaces();
    }

    public BookingEntity() {}

    public BookingEntity user(String user) {
        this.user = user;
        return this;
    }
    /**
     * Get user
     * @return user
     **/
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public BookingEntity id(Integer id) {
        this.id = id;
        return this;
    }
    /**
     * Get id
     * @return id
     **/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public BookingEntity activity(Integer activity) {
        this.activity = activity;
        return this;
    }

    /**
     * Get activity
     * @return activity
     **/
    @NotNull
    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    

    public BookingEntity location(Integer location) {
        this.location = location;
        return this;
    }
    /**
     * Get location
     * @return location
     **/
    @NotNull
    public Integer getlocation() {
        return location;
    }
    public void setlocation(Integer location) {
        this.location = location;
    }



    public BookingEntity date(String date) {
        this.date = date;
        return this;
    }
    /**
     * Get date
     * @return date
     **/
    @NotNull
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public BookingEntity nbPlaces(Integer nbPlaces) {
        this.nbPlaces = nbPlaces;
        return this;
    }
    /**
     * Get nbPlaces
     * @return nbPlaces
     **/
    @NotNull
    public Integer getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(Integer nbPlaces) {
        this.nbPlaces = nbPlaces;
    }


}
