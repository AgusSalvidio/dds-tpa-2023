package ar.edu.utn.frba.dds.ranking.rankingcomparators;

import ar.edu.utn.frba.dds.entity.EntityIncidentSummary;
import ar.edu.utn.frba.dds.incident.IncidentTimeCalculator;
import java.io.Serializable;
import java.util.Comparator;

public class AverageClosingTimeComparator implements Comparator<EntityIncidentSummary>,
                                                     Serializable {

  @Override
  public int compare(
      EntityIncidentSummary firstEntityIncidentSummary,
      EntityIncidentSummary secondEntityIncidentSummary) {
    IncidentTimeCalculator incidentTimeCalculator = new IncidentTimeCalculator();

    return Double
        .compare(
            incidentTimeCalculator.averageClosingTimeOf(firstEntityIncidentSummary
                .incidentPerCommunity()
                .community().openIncidents()),
            incidentTimeCalculator.averageClosingTimeOf(secondEntityIncidentSummary
                .incidentPerCommunity()
                .community().openIncidents())
        );
  }

}
