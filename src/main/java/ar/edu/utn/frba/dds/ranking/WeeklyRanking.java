package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.entity.EntityIncidentSummary;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ranking")
public class WeeklyRanking {
  @Id
  @GeneratedValue
  @Setter
  @Getter
  Integer id;

  @Getter
  @ManyToMany
  @JoinColumn(name = "entity_incident_id", referencedColumnName = "id")
  public List<EntityIncidentSummary> entityIncidentSummaries;

  @Transient
  public Comparator<EntityIncidentSummary> rankingComparator;

  public WeeklyRanking(){}

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

  public void synchronizeWith(WeeklyRanking updateWeeklyRanking) {
    this.entityIncidentSummaries = updateWeeklyRanking.entityIncidentSummaries();
  }

}
