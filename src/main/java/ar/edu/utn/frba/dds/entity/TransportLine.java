package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.converters.DirectionConverter;
import ar.edu.utn.frba.dds.establishment.Establishment;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@DiscriminatorValue("transport_line")
@Setter
@Getter
public class TransportLine extends Entity {

  @OneToOne
  @JoinColumn(name = "establishment_departure_id", referencedColumnName = "id")
  //@Transient
  public Establishment departure;

  @OneToOne
  @JoinColumn(name = "establishment_arrival_id", referencedColumnName = "id")
  //@Transient
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
