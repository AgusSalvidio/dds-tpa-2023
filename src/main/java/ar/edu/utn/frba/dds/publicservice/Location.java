package ar.edu.utn.frba.dds.publicservice;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Location {
  private String address;
  private String city;
  private Double latitude;
  private Double longitude;

  public Location() {
    this.latitude = 0.0;
    this.longitude = 0.0;
  }
}
