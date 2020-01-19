package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * Booking
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-01-05T20:58:36.237Z[GMT]")
public class Booking   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("activityId")
  private Integer activityId = null;

  @JsonProperty("activityName")
  private String activityName = null;

  @JsonProperty("locationId")
  private Integer locationId = null;

  @JsonProperty("locationName")
  private String locationName = null;

  @JsonProperty("date")
  private String date = null;

  @JsonProperty("nbPlaces")
  private Integer nbPlaces = null;

  public Booking( BookingEntity b, String activityName, String locationName) {
    this.id = b.getId();
    this.activityId = b.getActivity();
    this.activityName = activityName;
    this.locationId = b.getlocation();
    this.locationName = locationName;
    this.date = b.getDate();
    this.nbPlaces = b.getNbPlaces();
  }
  // default constructor needed for requests
  public Booking () {}

  public Booking id(Integer id) {
    this.id = id;
    return this;
  }
  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Booking activityId(Integer activityId) {
    this.activityId = activityId;
    return this;
  }
  /**
   * Get activityId
   * @return activityId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getActivityId() {
    return activityId;
  }

  public void setActivityId(Integer activityId) {
    this.activityId = activityId;
  }


  public Booking activityName(String activityName) {
    this.activityName = activityName;
    return this;
  }
  /**
   * Get activityName
   * @return activityName
  **/
  @ApiModelProperty(value = "")
    public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }


  public Booking locationId(Integer locationId) {
    this.locationId = locationId;
    return this;
  }
  /**
   * Get locationId
   * @return locationId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getLocationId() {
    return locationId;
  }

  public void setLocationId(Integer locationId) {
    this.locationId = locationId;
  }


  public Booking locationName(String locationName) {
    this.locationName = locationName;
    return this;
  }
  /**
   * Get locationName
   * @return locationName
  **/
  @ApiModelProperty(value = "")
  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }


  public Booking date(String date) {
    this.date = date;
    return this;
  }
  /**
   * Get date
   * @return date
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Pattern(regexp = "\\d{8}")
  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }


  public Booking nbPlaces(Integer nbPlaces) {
    this.nbPlaces = nbPlaces;
    return this;
  }
  /**
   * Get nbPlaces
   * @return nbPlaces
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Min(1)
  public Integer getNbPlaces() {
    return nbPlaces;
  }

  public void setNbPlaces(Integer nbPlaces) {
    this.nbPlaces = nbPlaces;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Booking booking = (Booking) o;
    return Objects.equals(this.id, booking.id) &&
        Objects.equals(this.activityId, booking.activityId) &&
        Objects.equals(this.activityName, booking.activityName) &&
        Objects.equals(this.locationId, booking.locationId) &&
        Objects.equals(this.locationName, booking.locationName) &&
        Objects.equals(this.date, booking.date) &&
        Objects.equals(this.nbPlaces, booking.nbPlaces);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, activityId, activityName, locationId, locationName, date, nbPlaces);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Booking {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    activityId: ").append(toIndentedString(activityId)).append("\n");
    sb.append("    activityName: ").append(toIndentedString(activityName)).append("\n");
    sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
    sb.append("    locationName: ").append(toIndentedString(locationName)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    nbPlaces: ").append(toIndentedString(nbPlaces)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
