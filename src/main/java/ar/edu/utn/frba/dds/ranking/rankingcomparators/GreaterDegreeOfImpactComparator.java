package ar.edu.utn.frba.dds.ranking.rankingcomparators;

import ar.edu.utn.frba.dds.entity.Entity;
import java.io.Serializable;
import java.util.Comparator;

public class GreaterDegreeOfImpactComparator implements Comparator<Entity>, Serializable {

  @Override
  public int compare(Entity firstEntity, Entity secondEntity) {

    //TODO: Criteria is defined in the next iteration
    return Double
        .compare(
            firstEntity.averageClosingTimeForIncidents(),
            secondEntity.averageClosingTimeForIncidents());
  }
}
