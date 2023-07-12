package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.incident.Incident;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

public abstract class Entity {
  @Setter
  @Getter
  public EntityType type;
  @Setter

  public EntityName name;
  public List<Establishment> establishments;
  public List<Incident> incidents;

  public Entity() {

    this.establishments = new ArrayList();
    this.incidents = new ArrayList<>();
  }

  public void addNewEstablishment(Establishment newEstablishment) {
    this.establishments.add(newEstablishment);
  }

  public List<Establishment> getEstablishments() {
    return this.establishments.stream().collect(Collectors.toList());
  }

  public String name() {
    return this.name.getName();
  }


  public void addNewIncident(Incident newIncident) {
    this.incidents.add(newIncident);
  }

  public List<Incident> incidents() {
    return this.incidents;
  }

  public double averageClosingTimeForIncidents() {
    OptionalDouble average;

    average = this.incidents.stream().mapToDouble(Incident::closingTime).average();

    return average.isPresent() ? average.getAsDouble() : 0;
  }

  public List<Incident> validIncidents() {
    List<Incident> incidentsWithMoreThan24Hours = new ArrayList<>();

    for (Incident incident : this.incidents) {
      if (incident.reported24HoursAgo() && incident.state().name().equals("OPEN")) {
        incidentsWithMoreThan24Hours.add(incident);
      }
    }

    return incidentsWithMoreThan24Hours;
  }

}
