package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.user.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class IncidentManagementSystem {
  MemoryBasedPersistenceSystem persistenceSystem;

  public IncidentManagementSystem(MemoryBasedPersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }

  public String typeDescription() {
    return "Sistema de Administración de Incidentes";
  }

  private MemoryBasedPersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public static IncidentManagementSystem workingWith(
      MemoryBasedPersistenceSystem persistenceSystem) {
    return new IncidentManagementSystem(persistenceSystem);
  }

  public void startManaging(Incident anIncident) {
    this.persistenceSystem().startManagingIncident(anIncident);
  }

  public void stopManaging(Incident anIncident) {
    this.persistenceSystem().stopManagingIncident(anIncident);
  }

  public List<Incident> incidents() {
    return this.persistenceSystem().incidents();
  }

  public void updateWith(Incident currentIncident, Incident updateIncident) {
    currentIncident.synchronizeWith(updateIncident);
  }

  public void startManagingIncidentForm(Map model) {
    Service service = (Service) model.get("service");
    LocalDateTime dateTime = (LocalDateTime) model.get("dateTime");
    String observation = model.get("observation").toString();
    User user = (User) model.get("user");

    this.startManaging(Incident.composedOf(service, observation, dateTime, user));
  }

  public void receiveFrom(NotifiableEvent event, Object publisher) {
    /* For now, this system should have an implementation. This will be enhanced
     when the extracting the implementation from ManagementSystem -asalvidio*/
  }
}
