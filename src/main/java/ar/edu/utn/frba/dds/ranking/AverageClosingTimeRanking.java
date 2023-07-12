package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.ranking.rankingcomparators.AverageClosingTimeComparator;

public class AverageClosingTimeRanking extends WeeklyRanking {

  public AverageClosingTimeRanking(AverageClosingTimeComparator rankingComparator) {
    super(rankingComparator);
  }

}
