package ar.edu.utn.frba.dds.incident;

import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.service.State;
import java.time.LocalDateTime;

public class IncidentPerCommunity {
  Incident incident;

  Community community;

  State state;

  LocalDateTime dateTime;

  public static IncidentPerCommunity composedOf(Incident incident, Community community) {
    return new IncidentPerCommunity(incident, community);
  }

  public Incident incident() {
    return this.incident;
  }

  public Community community() {
    return this.community;
  }

  public State state() {
    return this.state;
  }

  public LocalDateTime dateTime() {
    return this.dateTime;
  }


  public IncidentPerCommunity(Incident incident, Community community) {
    this.incident = incident;
    this.community = community;
    this.state = State.composedOf("OPEN", "Open Incident");
    this.dateTime = LocalDateTime.now();
  }

  public void synchronizeWith(IncidentPerCommunity updatedIncidentPerCommunity) {
    this.incident = updatedIncidentPerCommunity.incident;
    this.community = updatedIncidentPerCommunity.community;
    this.state = updatedIncidentPerCommunity.state;
    this.dateTime = updatedIncidentPerCommunity.dateTime;
  }

  public void close() {
    this.state = State.composedOf("CLOSED", "Closed Incident");
    this.dateTime = LocalDateTime.now();
  }

}
