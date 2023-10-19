package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.establishment.Establishment;
import java.util.ArrayList;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Table(name = "transport_line")
public class TransportLine extends Entity {
  @Id
  @GeneratedValue
  Integer id;

  @Setter
  @Getter
  @OneToOne
  @JoinColumn(name = "departure_id", referencedColumnName = "id")
  public Establishment departure;

  @Setter
  @Getter
  @OneToOne
  @JoinColumn(name = "arrival_id", referencedColumnName = "id")
  public Establishment arrival;

  @Setter
  @Getter
  @Enumerated(EnumType.STRING)
  public Direction direction;

  public static TransportLine composedOf(
      Establishment departure,
      Establishment arrival, Direction direction) {
    return new TransportLine(departure, arrival, direction);
  }

  public TransportLine() {}

  public TransportLine(Establishment departure, Establishment arrival, Direction direction) {
    this.establishments = new ArrayList<>();
    this.incidents = new ArrayList<>();
    this.departure = departure;
    this.arrival = arrival;
    this.direction = direction;
  }
}
