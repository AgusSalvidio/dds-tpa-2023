package ar.edu.utn.frba.dds.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
