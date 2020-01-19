package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * ActivityResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-01-05T20:58:36.237Z[GMT]")
public class ActivityResult   {
  @JsonProperty("activity")
  private Activity activity = null;

  @JsonProperty("location")
  private Location location = null;

  @JsonProperty("placesLeft")
  private Integer placesLeft = null;


  public ActivityResult(Activity activity, Location location, Integer placesLeft) {
    this.activity = activity;
    this.location = location;
    this.placesLeft = placesLeft;
  }

  public ActivityResult activity(Activity activity) {
    this.activity = activity;
    return this;
  }

  /**
   * Get activity
   * @return activity
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Activity getActivity() {
    return activity;
  }

  public void setActivity(Activity activity) {
    this.activity = activity;
  }

  public ActivityResult location(Location location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public ActivityResult placesLeft(Integer placesLeft) {
    this.placesLeft = placesLeft;
    return this;
  }

  /**
   * Get placesLeft
   * @return placesLeft
  **/
  @ApiModelProperty(value = "")
  
    public Integer getPlacesLeft() {
    return placesLeft;
  }

  public void setPlacesLeft(Integer placesLeft) {
    this.placesLeft = placesLeft;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActivityResult activityResult = (ActivityResult) o;
    return Objects.equals(this.activity, activityResult.activity) &&
        Objects.equals(this.location, activityResult.location) &&
        Objects.equals(this.placesLeft, activityResult.placesLeft);
  }

  @Override
  public int hashCode() {
    return Objects.hash(activity, location, placesLeft);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActivityResult {\n");
    
    sb.append("    activity: ").append(toIndentedString(activity)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    placesLeft: ").append(toIndentedString(placesLeft)).append("\n");
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
