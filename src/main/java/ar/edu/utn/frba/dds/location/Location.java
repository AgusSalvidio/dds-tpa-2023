package ar.edu.utn.frba.dds.location;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "location")
@Setter
@Getter
public class Location {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @Column(name = "street")
  public String street;

  @Column(name = "number")
  public Integer number;

  @OneToOne
  @JoinColumn(name = "municipality_id", referencedColumnName = "id")
  public Municipality municipality;

  public Location() {
    this.number = 0;
  }
}
