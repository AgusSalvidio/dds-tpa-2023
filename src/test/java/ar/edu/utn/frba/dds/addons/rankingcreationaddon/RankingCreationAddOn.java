package ar.edu.utn.frba.dds.addons.rankingcreationaddon;

import ar.edu.utn.frba.dds.ranking.AverageClosingTimeRanking;
import ar.edu.utn.frba.dds.ranking.GreaterDegreeOfImpactRanking;
import ar.edu.utn.frba.dds.ranking.MostReportedIncidentsRanking;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.AverageClosingTimeComparator;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.CommunityComparator;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.GreaterDegreeOfImpactComparator;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.MostReportedIncidentsComparator;

public class RankingCreationAddOn {

  public AverageClosingTimeRanking averageClosingTimeRanking() {
//    AverageClosingTimeRanking averageRanking =
    return new AverageClosingTimeRanking(new AverageClosingTimeComparator());

//    averageRanking.addEntityToRanking(new EntityCreationAddOn().entityA());
//    averageRanking.addEntityToRanking(new EntityCreationAddOn().entityB());

//    return averageRanking;
  }

  public GreaterDegreeOfImpactRanking greaterDegreeOfImpactRanking() {
//    GreaterDegreeOfImpactRanking greaterDegreeRanking =
    return new GreaterDegreeOfImpactRanking(
        new GreaterDegreeOfImpactComparator(),
        new CommunityComparator()
    );

//    greaterDegreeRanking.addNewCommunity(new CommunityCreationAddOn().firstCommunity());
//    greaterDegreeRanking.addNewCommunity(new CommunityCreationAddOn().secondCommunity());
//
//    greaterDegreeRanking.sortCommunitiesBasedOnComparator();
//
//    return greaterDegreeRanking;
  }

  public MostReportedIncidentsRanking mostReportedIncidentsRanking() {
//    MostReportedIncidentsRanking mostReportedRanking =
    return new MostReportedIncidentsRanking(new MostReportedIncidentsComparator());

//    mostReportedRanking.addEntityToRanking(new EntityCreationAddOn().entityA());
//    mostReportedRanking.addEntityToRanking(new EntityCreationAddOn().entityC());
//
//    return mostReportedRanking;
  }

}
