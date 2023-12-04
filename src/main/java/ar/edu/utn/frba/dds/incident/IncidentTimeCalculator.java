package ar.edu.utn.frba.dds.incident;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class IncidentTimeCalculator {

  public List<Incident> incidentsWithMoreThan24Hours(List<Incident> incidents) {
    List<Incident> incidentsWithMoreThan24Hours = new ArrayList<>();

    for (Incident incident : incidents) {
      //if (incident.reported24HoursAgo()) { incidentsWithMoreThan24Hours.add(incident);
      //}
    }

    //return incidentsWithMoreThan24Hours;
    return null;
  }

  public Integer averageClosingTimeOf(List<Incident> incidents) {
    OptionalDouble average;

    //average = incidents.stream().mapToDouble(Incident::closingTime).average();


    //return average.isPresent() ? average.getAsDouble() : 0;

    return 0;
  }
}
