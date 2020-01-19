package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Activity
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-01-05T20:58:36.237Z[GMT]")
public class Activity   {
  @Id
  @JsonProperty("id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("maxPlaces")
  private Integer maxPlaces = null;

  public Activity id(Integer id) {
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


  public Activity name(String name) {
    this.name = name;
    return this;
  }
  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Size(min=3)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Activity description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Size(min=10)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Activity maxPlaces(Integer maxPlaces) {
    this.maxPlaces = maxPlaces;
    return this;
  }

  /**
   * Get maxPlaces
   * minimum: 1
   * @return maxPlaces
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Min(1)
  public Integer getMaxPlaces() {
    return maxPlaces;
  }

  public void setMaxPlaces(Integer maxPlaces) {
    this.maxPlaces = maxPlaces;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Activity activity = (Activity) o;
    return Objects.equals(this.id, activity.id) &&
        Objects.equals(this.name, activity.name) &&
        Objects.equals(this.description, activity.description) &&
        Objects.equals(this.maxPlaces, activity.maxPlaces);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, maxPlaces);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Activity {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    maxPlaces: ").append(toIndentedString(maxPlaces)).append("\n");
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
