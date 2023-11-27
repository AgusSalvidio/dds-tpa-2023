package ar.edu.utn.frba.dds.incident;

import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.service.State;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Table(name = "incident_per_community")
public class IncidentPerCommunity {
  @Id
  @GeneratedValue
  @Setter
  @Getter
  Integer id;

  @Getter
  @OneToOne
  @JoinColumn(name = "incident_id", referencedColumnName = "id")
  Incident incident;

  @Getter
  @OneToOne
  @JoinColumn(name = "community_id", referencedColumnName = "id")
  Community community;

  @Setter
  @Getter
  @OneToOne
  @JoinColumn(name = "state_id", referencedColumnName = "id")
  State state;

  @Getter
  @Column(name = "dateTime")
  LocalDateTime dateTime;

  public static IncidentPerCommunity composedOf(Incident incident, Community community) {
    return new IncidentPerCommunity(incident, community);
  }

  public IncidentPerCommunity() {}

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
