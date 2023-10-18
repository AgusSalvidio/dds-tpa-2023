package ar.edu.utn.frba.dds.establishment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EstablishmentType {
  @Id
  @GeneratedValue
  Integer id;

  @Getter
  @Column(name = "name")
  public String name;

}
