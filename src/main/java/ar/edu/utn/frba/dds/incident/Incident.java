package ar.edu.utn.frba.dds.incident;

import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.user.User;
import java.time.LocalDateTime;

public class Incident {
  Service service;

  /*For now its gonna use the State class also used by Service(basically a string).
  In another issue this should have more logic. -asalvidio*/
  State state;
  LocalDateTime dateTime;

  String observations;

  User user;

  public static Incident composedOf(
      Service service,
      State state,
      String observations,
      LocalDateTime localDateTime,
      User user) {
    return new Incident(service, state, observations, localDateTime, user);
  }

  public Incident(
      Service service,
      State state,
      String observations,
      LocalDateTime dateTime,
      User user) {
    this.service = service;
    this.state = state;
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

  public State state() {
    return this.state;
  }

  public User user() {
    return this.user;
  }

}
