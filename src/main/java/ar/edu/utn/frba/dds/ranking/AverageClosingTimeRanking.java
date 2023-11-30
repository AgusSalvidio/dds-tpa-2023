package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.ranking.rankingcomparators.AverageClosingTimeComparator;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Table(name = "average_closing_time_ranking")
public class AverageClosingTimeRanking extends WeeklyRanking {

  @Id
  @GeneratedValue
  @Setter
  @Getter
  Integer id;

  public static AverageClosingTimeRanking composedOf(AverageClosingTimeComparator comparator) {
    return new AverageClosingTimeRanking(comparator);
  }

  public AverageClosingTimeRanking() {

  }

  public AverageClosingTimeRanking(AverageClosingTimeComparator rankingComparator) {
    super(rankingComparator);
  }

}
