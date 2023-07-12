package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.incident.Incident;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public abstract class Entity {
  @Setter
  @Getter
  public EntityType type;
  @Setter

  public EntityName name;
  public List<Establishment> establishments;
  public List<Incident> incidents;

  public Entity() {

    this.establishments = new ArrayList<>();
    this.incidents = new ArrayList<>();
  }

  public void addNewEstablishment(Establishment newEstablishment) {
    this.establishments.add(newEstablishment);
  }

  public List<Establishment> getEstablishments() {
    return this.establishments;
  }

  public String name() {
    return this.name.getName();
  }


  public void addNewIncident(Incident newIncident) {
    this.incidents.add(newIncident);
  }

  public List<Incident> incidents() {
    return this.incidents;
  }

}
