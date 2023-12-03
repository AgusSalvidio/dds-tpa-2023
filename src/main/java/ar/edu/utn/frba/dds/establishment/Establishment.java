package ar.edu.utn.frba.dds.establishment;

import ar.edu.utn.frba.dds.location.Location;
import ar.edu.utn.frba.dds.service.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "establishment")
@Getter
@Setter
public class Establishment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @OneToOne
  @JoinColumn(name = "establishment_type_id", referencedColumnName = "id")
  public EstablishmentType type;

  @Column(name = "name")
  public String name;

  @OneToOne
  @JoinColumn(name = "location_id", referencedColumnName = "id")
  public Location location;

  @ManyToMany
  public List<Service> services;

  public static Establishment composedOf(
      EstablishmentType establishmentType,
      String name,
      Location location) {
    return new Establishment(establishmentType, name, location);
  }

  public Establishment() {
    this.services = new ArrayList<>();
  }

  public Establishment(EstablishmentType type, String name, Location location) {
    this.type = type;
    this.name = name;
    this.location = location;
    this.services = new ArrayList<>();
  }

  public void addNewService(Service newService) {
    this.services.add(newService);
  }

  public List<Service> getServices() {
    return this.services.stream().collect(Collectors.toList());
  }
}
