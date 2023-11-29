package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.incident.Incident;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import ar.edu.utn.frba.dds.serviceholder.Company;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Table(name = "entity")
@Getter
@Setter
public abstract class Entity {

  @Id
  @GeneratedValue
  Integer id;

  @Transient
  public EntityType type;

  public String name;

  @Transient
  //@OneToMany
  //@JoinColumn(name = "establishment_id", referencedColumnName = "id")
  public List<Establishment> establishments;

  @Transient
  //@OneToMany
  //@JoinColumn(name = "incident_id", referencedColumnName = "id")
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
    //return this.name.getName();
    return this.name();
  }

  public void addNewIncident(Incident newIncident) {
    this.incidents.add(newIncident);
  }

  public List<Incident> incidents() {
    return this.incidents;
  }

  public List<Establishment> establishments() {
    return this.establishments;
  }

  public void synchronizedWith(Entity updateEntity) {
    this.type = updateEntity.getType();
    this.name = updateEntity.getName();
    this.establishments = updateEntity.establishments();
    this.incidents = updateEntity.incidents();
  }
}
