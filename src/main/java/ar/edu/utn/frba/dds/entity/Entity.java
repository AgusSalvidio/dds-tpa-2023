package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.incident.Incident;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Table(name = "entity")
public abstract class Entity {

  @Id
  @GeneratedValue
  Integer id;

  @Getter
  @Setter
  @Transient
  public EntityType type;

  @Getter
  @Setter
  @OneToOne
  public EntityName name;

  @OneToMany
  @JoinColumn(name = "establishment_id", referencedColumnName = "id")
  public List<Establishment> establishments;

  @Getter
  @OneToMany
  @JoinColumn(name = "incident_id", referencedColumnName = "id")
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
