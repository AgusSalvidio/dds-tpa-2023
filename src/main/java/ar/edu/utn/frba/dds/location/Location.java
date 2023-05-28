package ar.edu.utn.frba.dds.location;

import ar.edu.utn.frba.dds.services.georef.entities.Municipality;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Location {
  public String street;
  public Integer number;
  public Municipality municipality;

  public Location() {
    this.number = 0;
  }
}
