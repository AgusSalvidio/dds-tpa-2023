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

@Setter
@Getter
public class TransportLine extends Entity {
  @Id
  @GeneratedValue
  Integer id;

  //@OneToOne
  //@JoinColumn(name = "departure_id", referencedColumnName = "id")
  public Establishment departure;

  //@OneToOne
  //@JoinColumn(name = "arrival_id", referencedColumnName = "id")
  public Establishment arrival;

  //@Enumerated(EnumType.STRING)
  public Direction direction;

  public static TransportLine composedOf(
      String name,
      Establishment departure,
      Establishment arrival, Direction direction) {
    return new TransportLine(name, departure, arrival, direction);
  }

  public TransportLine() {}

  public TransportLine(String name, Establishment departure, Establishment arrival, Direction direction) {
    this.name = name;
    this.establishments = new ArrayList<>();
    this.incidents = new ArrayList<>();
    this.departure = departure;
    this.arrival = arrival;
    this.direction = direction;
  }
}
