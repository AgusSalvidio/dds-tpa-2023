package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

//@javax.persistence.Entity
//@Table(name = "entity_incident_summary")
public class EntityIncidentSummary {
  @Id
  @GeneratedValue
  @Setter
  @Getter
  Integer id;

  @Getter
  //@ManyToOne
  //@JoinColumn(name = "entity_id", referencedColumnName = "id")
  //@JoinTable(name = "entity", joinColumns = { @JoinColumn(name = "entity_id", referencedColumnName = "id") })
  public Entity entity;

  @Getter
  //@OneToOne
  public IncidentPerCommunity incidentPerCommunity;

  public EntityIncidentSummary() {

  }

  public EntityIncidentSummary(Entity entity, IncidentPerCommunity incidentPerCommunity) {
    this.entity = entity;
    this.incidentPerCommunity = incidentPerCommunity;
  }

  public Entity entity() {
    return this.entity;
  }

  public IncidentPerCommunity incidentPerCommunity() {
    return this.incidentPerCommunity;
  }

}
