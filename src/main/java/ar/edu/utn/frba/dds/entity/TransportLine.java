package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.establishment.Establishment;
import lombok.Getter;
import lombok.Setter;

public class TransportLine extends Entity {
  @Setter
  @Getter
  public Establishment departure;
  @Setter
  @Getter
  public Establishment arrival;
  @Setter
  @Getter
  public Direction direction;
}
