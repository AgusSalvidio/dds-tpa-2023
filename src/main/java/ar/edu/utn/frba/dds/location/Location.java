package ar.edu.utn.frba.dds.location;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Location {
  public String street;
  public Integer number;
  public City city;
  public Country country;
  public Double latitude;
  public Double longitude;

  public Location() {
    this.number = 0;
    this.latitude = 0.0;
    this.longitude = 0.0;
  }
}
