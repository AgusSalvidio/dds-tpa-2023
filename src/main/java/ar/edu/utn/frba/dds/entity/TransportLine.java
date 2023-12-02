package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.converters.DirectionConverter;
import ar.edu.utn.frba.dds.establishment.Establishment;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Convert;
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

  @Convert(converter = DirectionConverter.class)
  @Column(name = "direction")
  public Direction direction;

  public static TransportLine composedOf(
      Establishment departure, Establishment arrival, Direction direction) {
    return new TransportLine(departure, arrival, direction);
  }

  public TransportLine() {
  }

  public TransportLine(Establishment departure, Establishment arrival, Direction direction) {
    this.departure = departure;
    this.arrival = arrival;
    this.direction = direction;
  }
}
