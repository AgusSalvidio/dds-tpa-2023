package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.establishment.Establishment;
import java.util.ArrayList;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@DiscriminatorValue("transport_line")
@Setter
@Getter
public class TransportLine extends Entity {

  @Transient
  public Establishment departure;

  @Transient
  public Establishment arrival;

  @Transient
  public Direction direction;

  public static TransportLine composedOf(
      Establishment departure,
      Establishment arrival, Direction direction) {
    return new TransportLine(departure, arrival, direction);
  }

  public TransportLine() {
  }

  public TransportLine(Establishment departure, Establishment arrival, Direction direction) {
    this.establishments = new ArrayList<>();
    this.incidents = new ArrayList<>();
    this.departure = departure;
    this.arrival = arrival;
    this.direction = direction;
  }
}
