package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EntityIncidentSummary {
  public Entity entity;

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
