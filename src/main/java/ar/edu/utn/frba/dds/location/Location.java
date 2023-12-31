package ar.edu.utn.frba.dds.location;

import ar.edu.utn.frba.dds.services.georef.entities.Municipality;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "location")
public class Location {
  @Id
  @GeneratedValue
  @Setter
  @Getter
  Integer id;

  @Column(name = "street")
  public String street;

  @Column(name = "number")
  public Integer number;

  @Transient
  public Municipality municipality;

  public Location() {
    this.number = 0;
  }
}
