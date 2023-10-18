package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import java.util.List;
import java.util.Map;

public class IncidentPerCommunityManagementSystem {
  RelationalDatabasePersistenceSystem persistenceSystem;

  public IncidentPerCommunityManagementSystem(
      RelationalDatabasePersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }

  public String typeDescription() {
    return "Sistema de Administración de Incidentes por comunidad";
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public static IncidentPerCommunityManagementSystem workingWith(
      RelationalDatabasePersistenceSystem persistenceSystem) {
    return new IncidentPerCommunityManagementSystem(persistenceSystem);
  }

  public void startManaging(IncidentPerCommunity incidentPerCommunity) {
    this.persistenceSystem().startManagingIncidentPerCommunity(incidentPerCommunity);
  }

  public void stopManaging(IncidentPerCommunity incidentPerCommunity) {
    this.persistenceSystem().stopManagingIncidentPerCommunity(incidentPerCommunity);
  }

  public List<IncidentPerCommunity> incidentsPerCommunity() {
    return this.persistenceSystem().incidentsPerCommunity();
  }

  public void updateWith(
      IncidentPerCommunity currentIncidentPerCommunity,
      IncidentPerCommunity updatedIncidentPerCommunity) {
    currentIncidentPerCommunity.synchronizeWith(updatedIncidentPerCommunity);
  }

  public void startManagingIncidentPerCommunityForm(Map model) {
    Incident incident = (Incident) model.get("observation");
    Community community = (Community) model.get("user");

    this.startManaging(IncidentPerCommunity.composedOf(incident, community));
  }

  public void receiveFrom(NotifiableEvent event, Object publisher) {
    /* For now, this system should have an implementation. This will be enhanced
     when the extracting the implementation from ManagementSystem -asalvidio*/
  }

}
