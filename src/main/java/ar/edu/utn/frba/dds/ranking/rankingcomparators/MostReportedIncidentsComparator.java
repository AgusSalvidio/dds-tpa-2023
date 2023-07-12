package ar.edu.utn.frba.dds.ranking.rankingcomparators;

import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.incident.IncidentTimeCalculator;
import java.io.Serializable;
import java.util.Comparator;

public class MostReportedIncidentsComparator implements Comparator<Entity>, Serializable {

  @Override
  public int compare(Entity firstEntity, Entity secondEntity) {

    IncidentTimeCalculator incidentTimeCalculator = new IncidentTimeCalculator();

    return Double
        .compare(
            incidentTimeCalculator.incidentsWithMoreThan24Hours(firstEntity.incidents()).size(),
            incidentTimeCalculator.incidentsWithMoreThan24Hours(secondEntity.incidents()).size()
        );
  }
}
