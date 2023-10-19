package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.entity.EntityIncidentSummary;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WeeklyRanking {

  public List<EntityIncidentSummary> entityIncidentSummaries;
  public Comparator<EntityIncidentSummary> rankingComparator;

  public WeeklyRanking(Comparator<EntityIncidentSummary> rankingComparator) {
    this.entityIncidentSummaries = new ArrayList<>();
    this.rankingComparator = rankingComparator;
  }

  public void addEntitySummaryToRanking(EntityIncidentSummary newEntitySummary) {
    this.entityIncidentSummaries.add(newEntitySummary);
  }

  public List<EntityIncidentSummary> entityIncidentSummaries() {
    return this.entityIncidentSummaries;
  }

  public Comparator<EntityIncidentSummary> rankingComparator() {
    return this.rankingComparator;
  }


  public void generateRanking() {
    this.entityIncidentSummaries.sort(this.rankingComparator);
  }

}
