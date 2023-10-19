package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.entity.EntityIncidentSummary;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.CommunityComparator;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.GreaterDegreeOfImpactComparator;
import java.util.ArrayList;
import java.util.List;

public class GreaterDegreeOfImpactRanking extends WeeklyRanking {

  public List<Community> communities;
  public CommunityComparator communityComparator;

  public GreaterDegreeOfImpactRanking(
      GreaterDegreeOfImpactComparator rankingComparator, CommunityComparator communityComparator) {

    super(rankingComparator);
    this.communities = new ArrayList<>();
    this.communityComparator = communityComparator;
  }

  @Override
  public void addEntitySummaryToRanking(EntityIncidentSummary newEntitySummary) {
    this.entityIncidentSummaries.add(newEntitySummary);
    this.communities.add(newEntitySummary.incidentPerCommunity().community());
  }

  public void addNewCommunity(Community newCommunity) {
    this.communities.add(newCommunity);
  }

  public List<Community> communities() {
    return this.communities;
  }

  public CommunityComparator communityComparator() {
    return this.communityComparator;
  }

  //This must be done before generating the rankings
  public void sortCommunitiesBasedOnComparator() {
    this.communities.sort(this.communityComparator);
  }

}