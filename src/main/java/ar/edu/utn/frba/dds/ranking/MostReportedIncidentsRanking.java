package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.ranking.rankingcomparators.MostReportedIncidentsComparator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Table(name = "most_reported_incidents_ranking")
public class MostReportedIncidentsRanking extends WeeklyRanking {

  @Id
  @GeneratedValue
  @Setter
  @Getter
  Integer id;

  public static MostReportedIncidentsRanking composedOf(
      MostReportedIncidentsComparator rankingComparator) {
    return new MostReportedIncidentsRanking(rankingComparator);
  }

  public MostReportedIncidentsRanking() {

  }

  public MostReportedIncidentsRanking(MostReportedIncidentsComparator rankingComparator) {
    super(rankingComparator);
  }
}
