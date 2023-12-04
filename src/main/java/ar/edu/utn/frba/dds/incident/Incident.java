package ar.edu.utn.frba.dds.incident;

import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.user.User;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "incident")
@Getter
@Setter
public class Incident {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @OneToOne
  @JoinColumn(name = "entity_id", referencedColumnName = "id")
  ar.edu.utn.frba.dds.entity.Entity entity;

  @OneToOne
  @JoinColumn(name = "establishment_id", referencedColumnName = "id")
  Establishment establishment;

  @OneToOne
  @JoinColumn(name = "service_id", referencedColumnName = "id")
  Service service;

  @Column(name = "report_date_time")
  LocalDateTime reportDateTime;

  @Column(name = "close_date_time")
  LocalDateTime closeDateTime;

  @Column(name = "time_incident")
  Double timeIncident;

  @Column(name = "observation")
  String observations;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  User user;

  @ManyToMany
  List<Community> communities;

  @Column(name = "is_close")
  Boolean isClosed;

  public Incident() {
    this.setCloseDateTime(null);
    this.setTimeIncident(0.0);
    this.setObservations("");
    this.communities = new ArrayList<>();
    this.setIsClosed(false);
  }

  public void closeIncident() {
    this.setIsClosed(true);
    this.setCloseDateTime(LocalDateTime.now());
    this.setTimeIncident(lifeTime());
  }

  public Double lifeTime() {
    return Double.valueOf(
        Math.abs(Duration.between(this.closeDateTime, this.reportDateTime).toMinutes()));
    /*
    Duration durationTime = Duration.between(this.closeDateTime, this.reportDateTime);
    Long hour = Math.abs(durationTime.toHours());
    return hour.intValue();
    */
  }

}
