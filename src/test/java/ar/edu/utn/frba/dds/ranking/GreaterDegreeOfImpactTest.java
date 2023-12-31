package ar.edu.utn.frba.dds.ranking;

import ar.edu.utn.frba.dds.addons.communitycreationaddon.CommunityCreationAddOn;
import ar.edu.utn.frba.dds.addons.entitycreationaddon.EntityCreationAddOn;
import ar.edu.utn.frba.dds.addons.incidentcreationaddon.IncidentCreationAddOn;
import ar.edu.utn.frba.dds.addons.rankingcreationaddon.RankingCreationAddOn;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.EntityIncidentSummary;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.CommunityComparator;
import ar.edu.utn.frba.dds.ranking.rankingcomparators.GreaterDegreeOfImpactComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class GreaterDegreeOfImpactTest {

  private GreaterDegreeOfImpactRanking greaterDegreeOfImpactRanking() {
    return new RankingCreationAddOn().greaterDegreeOfImpactRanking();
  }

  @Test
  @DisplayName("Create a ranking")
  public void createARankingTest() {
    GreaterDegreeOfImpactComparator greaterDegreeOfImpactComparator = new GreaterDegreeOfImpactComparator();
    CommunityComparator communityComparator = new CommunityComparator();
    GreaterDegreeOfImpactRanking greaterDegreeOfImpactRanking = new GreaterDegreeOfImpactRanking(greaterDegreeOfImpactComparator, communityComparator);

    Assertions.assertTrue(greaterDegreeOfImpactRanking.entityIncidentSummaries().isEmpty());
    Assertions.assertTrue(greaterDegreeOfImpactRanking.communities().isEmpty());
    Assertions.assertEquals(greaterDegreeOfImpactRanking.rankingComparator(), greaterDegreeOfImpactComparator);
    Assertions.assertEquals(greaterDegreeOfImpactRanking.communityComparator(), communityComparator);
  }

  @Test
  @DisplayName("Add new community to ranking")
  public void addCommunityToRanking() throws Exception {
    GreaterDegreeOfImpactComparator greaterDegreeOfImpactComparator = new GreaterDegreeOfImpactComparator();
    CommunityComparator communityComparator = new CommunityComparator();
    GreaterDegreeOfImpactRanking greaterDegreeOfImpactRanking = new GreaterDegreeOfImpactRanking(greaterDegreeOfImpactComparator, communityComparator);

    Community communityTest = new CommunityCreationAddOn().secondCommunity();

    Assertions.assertTrue(greaterDegreeOfImpactRanking.entityIncidentSummaries().isEmpty());

    greaterDegreeOfImpactRanking.addNewCommunity(communityTest);

    Assertions.assertTrue(greaterDegreeOfImpactRanking.communities().stream().anyMatch(community -> community.name().equals(communityTest.name())));
  }

  @Test
  @DisplayName("After generating the ranking, the entities are sorted in order")
  public void afterGeneratingTheRankingTheEntitiesAreSorted() throws Exception {
    GreaterDegreeOfImpactRanking greaterDegreeOfImpactRanking = this.greaterDegreeOfImpactRanking();
    Entity entityA = new EntityCreationAddOn().entityA();
    Entity entityB = new EntityCreationAddOn().entityB();
    Community firstCommunity = new CommunityCreationAddOn().firstCommunity();
    Community secondCommunity = new CommunityCreationAddOn().secondCommunity();

    firstCommunity.addTransportLine(entityB);
    firstCommunity.addTransportLine(entityA);
    secondCommunity.addTransportLine(entityA);

    Incident incident = new IncidentCreationAddOn().notWorkingElevatorIncident();
    IncidentPerCommunity incidentPerCommunityA = IncidentPerCommunity.composedOf(incident, firstCommunity);
    IncidentPerCommunity incidentPerCommunityB = IncidentPerCommunity.composedOf(incident, secondCommunity);

    EntityIncidentSummary entityIncidentSummaryA = new EntityIncidentSummary(entityA, incidentPerCommunityA);
    EntityIncidentSummary entityIncidentSummaryB = new EntityIncidentSummary(entityB, incidentPerCommunityB);

    greaterDegreeOfImpactRanking.addEntitySummaryToRanking(entityIncidentSummaryB);
    greaterDegreeOfImpactRanking.addEntitySummaryToRanking(entityIncidentSummaryA);


    Assertions.assertEquals(
        greaterDegreeOfImpactRanking.communities(),
        Arrays.asList(secondCommunity, firstCommunity));

    greaterDegreeOfImpactRanking.sortCommunitiesBasedOnComparator();
    greaterDegreeOfImpactRanking.generateRanking();

    Assertions.assertEquals(
        greaterDegreeOfImpactRanking.communities(),
        Arrays.asList(firstCommunity, secondCommunity));
    Assertions.assertEquals(greaterDegreeOfImpactRanking.entityIncidentSummaries(), Arrays.asList(entityIncidentSummaryB, entityIncidentSummaryA));
  }
}
