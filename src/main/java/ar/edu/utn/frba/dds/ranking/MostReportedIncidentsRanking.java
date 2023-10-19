package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.ranking.rankingcomparators.MostReportedIncidentsComparator;

public class MostReportedIncidentsRanking extends WeeklyRanking {

  public MostReportedIncidentsRanking(MostReportedIncidentsComparator rankingComparator) {
    super(rankingComparator);
  }
}
