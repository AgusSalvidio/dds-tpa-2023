package ar.edu.utn.frba.dds.establishment;

import ar.edu.utn.frba.dds.location.Location;
import ar.edu.utn.frba.dds.service.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "establishment")
public class Establishment {
  @Id
  @GeneratedValue
  @Setter
  @Getter
  Integer id;

  @Setter
  @Getter
  @Transient
  public EstablishmentType type;

  @Setter
  @Getter
  @Column(name = "name")
  public String name;

  @Setter
  @Getter
  @OneToOne
  @JoinColumn(name = "location_id", referencedColumnName = "id")
  public Location location;

  @OneToMany
  @JoinColumn(name = "service_id", referencedColumnName = "id")
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
