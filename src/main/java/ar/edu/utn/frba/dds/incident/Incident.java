package ar.edu.utn.frba.dds.incident;

import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.user.User;
import java.time.LocalDateTime;

public class Incident {
  Service service;
  LocalDateTime dateTime;

  String observations;

  User user;

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

}
