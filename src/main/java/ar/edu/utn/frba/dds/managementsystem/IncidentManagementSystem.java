package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.user.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class IncidentManagementSystem {
  RelationalDatabasePersistenceSystem persistenceSystem;

  public IncidentManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }

  public String typeDescription() {
    return "Sistema de Administración de Incidentes";
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public static IncidentManagementSystem workingWith(
      RelationalDatabasePersistenceSystem persistenceSystem) {
    return new IncidentManagementSystem(persistenceSystem);
  }

  public void startManaging(Incident anIncident) {
    this.persistenceSystem().startManagingIncident(anIncident);
  }

  public void startManagingIncidentPerCommunity(Incident incident, Community community) {

    IncidentPerCommunity incidentPerCommunity =
        IncidentPerCommunity.composedOf(incident, community);

    this.persistenceSystem().startManagingIncidentPerCommunity(incidentPerCommunity);
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

  public void startManagingIncidentFrom(Map model) {
    String serviceIdStr = (String) model.get("service");
    Integer serviceId = Integer.parseInt(serviceIdStr);

    Service service = this.persistenceSystem.serviceIdentifiedBy(serviceId);
    LocalDateTime dateTime = (LocalDateTime) model.get("datetime");

    String observation = (String) model.get("observations");
    User user = (User) model.get("user");

    String communityIdStr = (String) model.get("community");
    Integer communityId = Integer.parseInt(communityIdStr);

    Community community = this.persistenceSystem.communityIdentifiedBy(communityId);

    Incident incident = Incident.composedOf(service, observation, dateTime, user);

    this.startManaging(incident);
    this.startManagingIncidentPerCommunity(incident, community);
  }

  public void receiveFrom(NotifiableEvent event, Object publisher) {
    /* For now, this system should have an implementation. This will be enhanced
     when the extracting the implementation from ManagementSystem -asalvidio*/
  }
}
