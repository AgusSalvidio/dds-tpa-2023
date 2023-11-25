package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "entity_incident_summary")
public class EntityIncidentSummary {
  @Id
  @GeneratedValue
  @Setter
  @Getter
  Integer id;

  @Getter
  @ManyToOne
  @JoinColumn(name = "entity_id", referencedColumnName = "id")
  public Entity entity;

  @Getter
  @OneToOne
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
