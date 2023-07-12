package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.eventnotificationsystem.EventNotificationSystem;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;
import java.util.ArrayList;
import java.util.List;

public class IncidentPerCommunityManagementSystem implements ManagementSystem {

  List<Object> systems;

  EventNotificationSystem eventNotificationSystem;

  public IncidentPerCommunityManagementSystem(
      PersistenceSystem persistenceSystem,
      EventNotificationSystem eventNotificationSystem) {
    this.systems = new ArrayList<>();
    this.eventNotificationSystem = eventNotificationSystem;
    this.systems.add(persistenceSystem);
    this.persistenceSystem().addObjectTypeToStore(IncidentPerCommunity.class.getName());
  }

  public String typeDescription() {
    return "Sistema de Administración de Incidentes por comunidad";
  }

  private PersistenceSystem persistenceSystem() {
    return (PersistenceSystem) this.systems.get(0);
  }

  public static IncidentPerCommunityManagementSystem workingWith(
      PersistenceSystem persistenceSystem,
      EventNotificationSystem eventNotificationSystem) {
    return new IncidentPerCommunityManagementSystem(persistenceSystem, eventNotificationSystem);
  }

  public void startManaging(Object incidentPerCommunity) {
    this.persistenceSystem().storeObjectTyped(
        incidentPerCommunity.getClass().getName(), incidentPerCommunity);
  }

  public void stopManaging(Object incidentPerCommunity) {
    this.persistenceSystem().removeObjectTyped(
        incidentPerCommunity.getClass().getName(), incidentPerCommunity);
  }

  public IncidentPerCommunity incidentPerCommunity(IncidentPerCommunity incidentPerCommunity) {
    return (IncidentPerCommunity) this.persistenceSystem()
        .findObjectTyped(incidentPerCommunity.getClass().getName(), incidentPerCommunity);
  }

  public List<Object> incidentsPerCommunity() {
    return this.persistenceSystem().objectsFrom(IncidentPerCommunity.class.getName());
  }

  public void updateWith(Object currentIncidentPerCommunity, Object updatedIncidentPerCommunity) {
    IncidentPerCommunity obtainedIncident = (IncidentPerCommunity) this.persistenceSystem()
        .findObjectTyped(currentIncidentPerCommunity.getClass().getName(),
            currentIncidentPerCommunity);
    obtainedIncident.synchronizeWith((IncidentPerCommunity) updatedIncidentPerCommunity);
  }

  private EventNotificationSystem eventNotificationSystem() {
    return this.eventNotificationSystem;
  }

  public void receiveFrom(NotifiableEvent event, Object publisher) {
    /* For now, this system should have an implementation. This will be enhanced
     when the extracting the implementation from ManagementSystem -asalvidio*/
  }

}
