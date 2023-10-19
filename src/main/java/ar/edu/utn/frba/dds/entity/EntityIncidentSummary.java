package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;

public class EntityIncidentSummary {
  public Entity entity;
  public IncidentPerCommunity incidentPerCommunity;

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
