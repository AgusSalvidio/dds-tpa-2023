package ar.edu.utn.frba.dds.incident;

import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.user.User;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "incident")
public class Incident {
  @Id
  @GeneratedValue
  @Getter
  @Setter
  Integer id;

  @Getter
  @OneToOne
  Service service;

  @Getter
  @Column(name = "date_time")
  LocalDateTime dateTime;

  @Getter
  @Column(name = "observation")
  String observations;

  @Getter
  @OneToOne
  User user;

  public Incident() {

  }

  public static Incident composedOf(
      Service service,
      String observations,
      LocalDateTime localDateTime,
      User user) {
    return new Incident(service, observations, localDateTime, user);
  }

  public Incident(
      Service service,
      String observations,
      LocalDateTime dateTime,
      User user) {
    this.service = service;
    this.observations = observations;
    this.dateTime = dateTime;
    this.user = user;
  }

  public Service service() {
    return this.service;
  }

  public LocalDateTime dateTime() {
    return this.dateTime;
  }

  public String observations() {
    return this.observations;
  }

  public User user() {
    return this.user;
  }

  public void synchronizeWith(Incident updatedIncident) {
    this.service = updatedIncident.service();
    this.observations = updatedIncident.observations();
    this.dateTime = updatedIncident.dateTime();
    this.user = updatedIncident.user();
  }

  public long closingTime() {
    LocalDateTime now = LocalDateTime.now();

    Duration durationOfIncident = Duration.between(now, this.dateTime);

    return Math.abs(durationOfIncident.toHours());
  }

  public boolean reported24HoursAgo() {
    LocalDateTime now = LocalDateTime.now();

    Duration durationOfIncident = Duration.between(now, this.dateTime);

    return Math.abs(durationOfIncident.toHours()) >= 24;
  }

}
