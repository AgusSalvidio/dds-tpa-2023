package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IncidentPerCommunityManagementSystem {
  MemoryBasedPersistenceSystem persistenceSystem;

  public IncidentPerCommunityManagementSystem(
      MemoryBasedPersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }

  public List<IncidentPerCommunity> incidentsPerCommunityFilteredBy(String state) {
    return this.persistenceSystem.incidentsPerCommunityFilteredBy(state);
  }

  public String typeDescription() {
    return "Sistema de Administración de Incidentes por comunidad";
  }

  private MemoryBasedPersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public static IncidentPerCommunityManagementSystem workingWith(
      MemoryBasedPersistenceSystem persistenceSystem) {
    return new IncidentPerCommunityManagementSystem(persistenceSystem);
  }

  public void startManaging(IncidentPerCommunity incidentPerCommunity) {
    this.persistenceSystem().startManagingIncidentPerCommunity(incidentPerCommunity);
  }

  public void stopManaging(IncidentPerCommunity incidentPerCommunity) {
    this.persistenceSystem().stopManagingIncidentPerCommunity(incidentPerCommunity);
  }

  public void closeIncidentPerCommunity(IncidentPerCommunity incidentPerCommunity) {
    this.persistenceSystem().closeIncidentPerCommunity(incidentPerCommunity);
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

  public IncidentPerCommunity incidentPerCommunityIdentifiedBy(Integer anId) {
    return this.persistenceSystem.incidentPerCommunityIdentifiedBy(anId);
  }

  public List<IncidentPerCommunity> incidentsFor(User anUser) {
    return this.incidentsPerCommunity().stream()
        .filter(incidentPerCommunity -> incidentPerCommunity.community().members().stream()
            .anyMatch(member -> member.user().equals(anUser)))
        .collect(Collectors.toList());
  }

}
