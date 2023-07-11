package ar.edu.utn.frba.dds.addons.notifiableeventaddon;

import ar.edu.utn.frba.dds.addons.incidentcreationaddon.IncidentCreationAddOn;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.IncidentAdded;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.incident.Incident;

public class NotifiableEventAddOn {

  private Incident notWorkingElevatorIncident() throws Exception {
    return new IncidentCreationAddOn().notWorkingElevatorIncident();
  }

  public NotifiableEvent incidentNotifiableEvent() throws Exception {
    return IncidentAdded.referringTo(this.notWorkingElevatorIncident());
  }

}
