package ar.edu.utn.frba.dds.ranking.rankingcomparators;

import ar.edu.utn.frba.dds.entity.EntityIncidentSummary;
import ar.edu.utn.frba.dds.incident.IncidentTimeCalculator;
import java.io.Serializable;
import java.util.Comparator;

public class MostReportedIncidentsComparator implements Comparator<EntityIncidentSummary>,
    Serializable {

  @Override
  public int compare(
      EntityIncidentSummary firstEntityIncidentSummary,
      EntityIncidentSummary secondEntityIncidentSummary) {
    IncidentTimeCalculator incidentTimeCalculator = new IncidentTimeCalculator();

    return Double
        .compare(
            incidentTimeCalculator.incidentsWithMoreThan24Hours(firstEntityIncidentSummary
                    .incidentPerCommunity()
                    .community().openIncidents())
                .size(),
            incidentTimeCalculator.incidentsWithMoreThan24Hours(secondEntityIncidentSummary
                    .incidentPerCommunity()
                    .community().openIncidents())
                .size()
        );
  }
}
