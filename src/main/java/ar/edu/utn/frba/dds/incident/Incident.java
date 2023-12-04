package ar.edu.utn.frba.dds.incident;

import ar.edu.utn.frba.dds.entity.*;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.user.User;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Entity;

import com.fasterxml.jackson.databind.exc.InvalidNullException;
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
  ar.edu.utn.frba.dds.entity.Entity entity;

  @OneToOne
  Establishment establishment;

  @OneToOne
  Service service;

  @Column(name = "report_date_time")
  LocalDateTime reportDateTime;

  @Column(name = "close_date_time")
  LocalDateTime closeDateTime;

  @Column(name = "time_incident")
  Integer timeIncident;

  @Column(name = "observation")
  String observations;

  @OneToOne
  User user;

  @ManyToMany
  List<Community> communities;

  @Column(name = "is_close")
  Boolean isClosed;

  public Incident() {
    this.setCloseDateTime(null);
    this.setTimeIncident(0);
    this.setObservations("");
    this.communities = new ArrayList<>();
    this.setIsClosed(false);
  }

  public void closeIncident() {
    this.setIsClosed(true);
    this.setCloseDateTime(LocalDateTime.now());
    this.setTimeIncident(lifeTime());
  }

  public Integer lifeTime() {
    Duration durationTime = Duration.between(this.closeDateTime, this.reportDateTime);
    Long hour = Math.abs(durationTime.toHours());
    return hour.intValue();
  }

}
