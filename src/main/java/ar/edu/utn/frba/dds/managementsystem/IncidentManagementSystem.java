package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.entity.*;
import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.user.User;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

  public void updateIncidentWith(Incident anIncident) {
    this.persistenceSystem().updateManaging(anIncident);
  }

  public void startManagingIncidentFrom(Map model) {
    Incident incident = new Incident();
    incident.setEntity(this.persistenceSystem.entityById(Integer.valueOf(model.get("entity").toString())));
    incident.setEstablishment(this.persistenceSystem.establishmentIdentifiedBy(Integer.valueOf(model.get("establishment").toString())));
    incident.setService(this.persistenceSystem.serviceIdentifiedBy(Integer.valueOf(model.get("service").toString())));
    incident.setReportDateTime((LocalDateTime) model.get("reportDateTime"));
    incident.setUser((User) model.get("user"));
    incident.setObservations((String) model.get("observations"));
    incident.setCommunities(this.persistenceSystem.communitiesByUser());
    this.startManaging(incident);
  }

  public void receiveFrom(NotifiableEvent event, Object publisher) {
    /* For now, this system should have an implementation. This will be enhanced
     when the extracting the implementation from ManagementSystem -asalvidio*/
  }
}
