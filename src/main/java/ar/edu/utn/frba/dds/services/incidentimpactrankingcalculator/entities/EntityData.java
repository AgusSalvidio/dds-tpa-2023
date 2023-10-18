package ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

public class EntityData {
  @Setter
  @Getter
  public String name;
  public List<Double> incidentsDuration;
  @Setter
  @Getter
  public int incidentsNotResolved;
  @Setter
  @Getter
  public int membersAffected;

  public EntityData() {
    this.incidentsDuration = new ArrayList<>();
  }

  public void addNewIncidentDuration(Double newIncidentDuration) {
    this.incidentsDuration.add(newIncidentDuration);
  }

  public List<Double> getIncidentsDuration() {
    return this.incidentsDuration.stream().collect(Collectors.toList());
  }
}
