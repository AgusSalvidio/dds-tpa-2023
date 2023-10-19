package ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent;

import ar.edu.utn.frba.dds.incident.Incident;

public class IncidentAdded implements NotifiableEvent {

  Incident incident;

  public static IncidentAdded referringTo(Incident incident) {
    return new IncidentAdded(incident);
  }

  public IncidentAdded(Incident incident) {
    this.incident = incident;
  }

  public Object associatedObject() {
    return this.incident;
  }

}
