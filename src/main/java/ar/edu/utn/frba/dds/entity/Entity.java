package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.incident.Incident;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entity_type")
@Table(name = "entity")
@Getter
@Setter
public abstract class Entity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @OneToOne
  @JoinColumn(name = "entity_name_id", referencedColumnName = "id")
  public EntityName name;

  @OneToOne
  @JoinColumn(name = "entity_type_id", referencedColumnName = "id")
  public EntityType type;

  @ManyToMany
  public List<Establishment> establishments;

  @Transient
  public List<Incident> incidents;

  public Entity() {
    this.establishments = new ArrayList<>();
    this.incidents = new ArrayList<>();
  }

  public void addNewEstablishment(Establishment newEstablishment) {
    this.establishments.add(newEstablishment);
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
