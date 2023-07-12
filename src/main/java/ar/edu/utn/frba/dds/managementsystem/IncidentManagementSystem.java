package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.eventnotificationsystem.EventNotificationSystem;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.IncidentAdded;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;
import java.util.ArrayList;
import java.util.List;

public class IncidentManagementSystem implements ManagementSystem {

  List<Object> systems;

  EventNotificationSystem eventNotificationSystem;

  public IncidentManagementSystem(
      PersistenceSystem persistenceSystem,
      EventNotificationSystem eventNotificationSystem) {
    this.systems = new ArrayList<>();
    this.eventNotificationSystem = eventNotificationSystem;
    this.systems.add(persistenceSystem);
    this.persistenceSystem().addObjectTypeToStore(Incident.class.getName());
  }

  public String typeDescription() {
    return "Sistema de Administración de Incidentes";
  }

  private PersistenceSystem persistenceSystem() {
    return (PersistenceSystem) this.systems.get(0);
  }

  public static IncidentManagementSystem workingWith(
      PersistenceSystem persistenceSystem,
      EventNotificationSystem eventNotificationSystem) {
    return new IncidentManagementSystem(persistenceSystem, eventNotificationSystem);
  }

  public IncidentAdded incidentAdded(Incident incident) {
    return IncidentAdded.referringTo(incident);
  }

  public void startManaging(Object incident) {
    this.persistenceSystem().storeObjectTyped(incident.getClass().getName(), incident);
    IncidentAdded incidentAdded = this.incidentAdded((Incident) incident);
    this.eventNotificationSystem.publishFrom(incidentAdded, this);
  }

  public void stopManaging(Object incident) {
    this.persistenceSystem().removeObjectTyped(incident.getClass().getName(), incident);
  }

  public Incident incident(Incident incident) {
    return (Incident) this.persistenceSystem()
        .findObjectTyped(incident.getClass().getName(), incident);
  }

  public List<Object> incidents() {
    return this.persistenceSystem().objectsFrom(Incident.class.getName());
  }

  public void updateWith(Object currentIncident, Object updatedIncident) {
    Incident obtainedIncident = (Incident) this.persistenceSystem()
        .findObjectTyped(currentIncident.getClass().getName(), currentIncident);
    obtainedIncident.synchronizeWith((Incident) updatedIncident);
  }

  public void receiveFrom(NotifiableEvent event, Object publisher) {
    /* For now, this system should have an implementation. This will be enhanced
     when the extracting the implementation from ManagementSystem -asalvidio*/
  }
}
