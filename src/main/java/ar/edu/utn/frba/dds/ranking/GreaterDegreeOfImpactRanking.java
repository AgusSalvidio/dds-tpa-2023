package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.entity.EntityIncidentSummary;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.CommunityComparator;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.GreaterDegreeOfImpactComparator;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "greater_degree_impact_ranking")
public class GreaterDegreeOfImpactRanking extends WeeklyRanking {

  @Id
  @GeneratedValue
  @Setter
  @Getter
  Integer id;

  @Getter
  @OneToMany
  @JoinColumn(name = "community_id", referencedColumnName = "id")
  public List<Community> communities;

  @Transient
  public CommunityComparator communityComparator;

  public static GreaterDegreeOfImpactRanking composedOf(GreaterDegreeOfImpactComparator rankingComparator, CommunityComparator communityComparator) {
    return new GreaterDegreeOfImpactRanking(rankingComparator, communityComparator);
  }

  public GreaterDegreeOfImpactRanking() {

  }

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