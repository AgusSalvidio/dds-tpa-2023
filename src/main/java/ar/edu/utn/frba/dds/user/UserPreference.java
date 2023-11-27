package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.location.Location;
import ar.edu.utn.frba.dds.service.Service;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@javax.persistence.Entity
@Table(name = "user_preference")
@Getter
@Setter
public class UserPreference {
  @Id
  @GeneratedValue
  Integer id;

  //@OneToMany
  //@JoinTable(name = "user_preference_service")
  //@JoinColumn(name = "user_id", referencedColumnName = "id")
  @Transient
  List<Service> services = new ArrayList<>();

  @Transient
  List<Entity> entities = new ArrayList<>();

  @Transient
  List<Location> locations = new ArrayList<>();

  public List<Service> services() {
    return this.services.stream()
        .collect(Collectors.toList());
  }

  public List<Entity> entities() {
    return this.entities.stream()
        .collect(Collectors.toList());
  }

  public void addService(Service service) {
    this.services.add(service);
  }

  public void addEntity(Entity entity) {
    this.entities.add(entity);
  }

  public void removeService(Service service) {
    this.services.remove(service);
  }

  public void removeEntity(Entity entity) {
    this.entities.remove(entity);
  }

  public void synchronizeWith(UserPreference anUpdaterUserPreference) {
    this.services = anUpdaterUserPreference.services();
    this.entities = anUpdaterUserPreference.entities();
  }

  public List<Location> locations() {
    return this.locations.stream()
        .collect(Collectors.toList());
  }

  public void addLocation(Location location) {
    this.locations.add(location);
  }

  public void removeLocation(Location location) {
    this.locations.remove(location);
  }


}
