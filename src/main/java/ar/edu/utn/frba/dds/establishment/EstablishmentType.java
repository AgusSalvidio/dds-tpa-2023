package ar.edu.utn.frba.dds.establishment;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "establishment_type")
@Getter
@Setter
public class EstablishmentType {
  @Id
  @GeneratedValue
  Integer id;

  @Column(name = "name")
  public String name;
}
